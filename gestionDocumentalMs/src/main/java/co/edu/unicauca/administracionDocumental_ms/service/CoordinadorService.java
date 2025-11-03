package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.factory.StateFactory;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.NotificationRequest;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.rabbitConfig.NotificationProducer;
import co.edu.unicauca.administracionDocumental_ms.rabbitConfig.PersonaConsumer;
import co.edu.unicauca.administracionDocumental_ms.repository.CoordinadorRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.DepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoordinadorService implements BaseService<Coordinador,String> {
    @Autowired
    CoordinadorRepository coordinadorRepository;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private NotificationClient notificationClient;
    @Autowired
    private NotificationProducer notificationProducer;

    private StateFactory stateFactory;

    @Override
    @Transactional
    public List<Coordinador> findAll() throws Exception {
        return List.of();
    }

    @Override
    @Transactional
    public Coordinador findById(String id) throws Exception {
        try {
            Optional<Coordinador> coordinador = coordinadorRepository.findByCorreoElectronico(id);

            System.out.println(coordinador);
            return coordinador.orElse(null);
        }catch(Exception ex){
            throw new Exception("Error al buscar el coordinador con id: "+id+" :"+ex.getMessage());
        }

    }

    @Override
    @Transactional
    public Coordinador save(Coordinador entity) throws Exception {
        try{
            return coordinadorRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar coordinador: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Coordinador updateById(Coordinador entity) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(String id) throws Exception {
        return false;
    }

    @Transactional
    public List<ProyectoDto> listaProyecto(String correoElectronico) throws Exception{
        try{
            List<ProyectoDto> listaProyectos;
            Optional<Coordinador> coordinadorC = coordinadorRepository.findByCorreoElectronico(correoElectronico);
            Coordinador coordinador = coordinadorC.orElse(null);
            if(coordinador != null)
            {
                listaProyectos = new ArrayList<ProyectoDto>();
                for(ProyectoDeGrado proyectoDeGrado: coordinador.getProyectosDeGrado()){
                    listaProyectos.add(proyectoService.mapearProyecto(proyectoDeGrado));
                }
                return listaProyectos;
            }else
            {
                throw new Exception("Coordinador no encontrado");
            }

        }catch(Exception ex){
            throw new Exception("Error al listar proyectos de grado: "+ex.getMessage());
        }
    }

    public ProyectoDeGrado aprobarFormatoA(ProyectoDeGrado proyectoDeGrado, Coordinador coordinador) throws Exception {


        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));
        if (proyectoDeGrado != null && proyectoDeGrado.getEstadoProyecto().equals("REVISION")) {
            coordinador.aprobarFormatoA(proyectoDeGrado);
            enviarNotificacionCambioEstado(proyectoDeGrado);
        }

        return proyectoDeGrado;
    }

    public ProyectoDeGrado rechazarFormatoA(ProyectoDeGrado proyectoDeGrado,Coordinador coordinador) throws Exception {
        proyectoDeGrado.setEstadoProyecto(stateFactory.getInstance().getInstance(proyectoDeGrado.getEstado()));

        if (proyectoDeGrado != null && proyectoDeGrado.getEstadoProyecto().equals("REVISION")) {
            coordinador.rechazarFormatoA(proyectoDeGrado);
            enviarNotificacionCambioEstado(proyectoDeGrado);
        }

        return proyectoDeGrado;
    }
    private void enviarNotificacionCambioEstado(ProyectoDeGrado proyecto) {
        NotificationRequest notification = new NotificationRequest();
        List<String> emails = new ArrayList<>();

        Profesor director = proyecto.getDirector();
        if (director != null && director.getCorreoElectronico() != null)
            emails.add(director.getCorreoElectronico());

        Estudiante estudiante1 = proyecto.getEstudiante1();
        if (proyecto.getEstudiante1() != null && proyecto.getEstudiante1().getCorreoElectronico() != null)
            emails.add(proyecto.getEstudiante1().getCorreoElectronico());

        Estudiante estudiante2 = proyecto.getEstudiante2();
        if(estudiante2!=null){
            if (proyecto.getEstudiante2() != null && proyecto.getEstudiante2().getCorreoElectronico() != null)
                emails.add(proyecto.getEstudiante2().getCorreoElectronico());
        }


        List<Profesor> codirectores = proyecto.getCodirectores();
        if (codirectores!=null){
            if (proyecto.getCodirectores() != null) {
                for (Profesor codirector : proyecto.getCodirectores()) {
                    if (codirector.getCorreoElectronico() != null)
                        emails.add(codirector.getCorreoElectronico());
                }
            }
        }

        if (proyecto.getCoordinador() != null && proyecto.getCoordinador().getCorreoElectronico() != null)
            emails.add(proyecto.getCoordinador().getCorreoElectronico());


        notification.setEmail(emails);
        notification.setSubject("Proyecto " + proyecto.getEstado() + ": " + proyecto.getTitulo());
        notification.setMessage(
                "El proyecto de grado titulado \"" + proyecto.getTitulo() + "\" ha sido " + proyecto.getEstado().toLowerCase() + ".\n\n" +
                        "Estado actual: " + proyecto.getEstado().toUpperCase() + "\n" +
                        "Director: " + director.getNombre() + " " + director.getApellido() + "\n\n" +
                        (codirectores != null && !codirectores.isEmpty() ?
                                "Codirectores: " + codirectores.stream().map(p -> p.getNombre() + " " + p.getApellido()).collect(Collectors.joining(", ")) + "\n"
                                :
                                "") +
                        (estudiante2!= null ?
                                "Estudiantes: " + estudiante1.getNombre() + " " + estudiante1.getApellido() + ", " + estudiante2.getNombre() + " " + estudiante2.getApellido() + "\n\n"
                                :
                                "Estudiante: " + estudiante1.getNombre() + " " + estudiante1.getApellido() + "\n\n") +
                        "Saludos,\nSistema de Proyectos"
        );

        try {
            notificationProducer.enviarNotificacion(notification);
        } catch (Exception e) {
            System.err.println(" Error al enviar notificación de cambio de estado: " + e.getMessage());
        }
    }
    public Coordinador mapearDto(PersonaDto personaDto)
    {
        Coordinador coordinador = new Coordinador();
        coordinador.setNombre(personaDto.getNombre());
        coordinador.setApellido(personaDto.getApellido());
        coordinador.setCelular(personaDto.getCelular());
        coordinador.setCorreoElectronico(personaDto.getCorreoElectronico());
        coordinador.relacionarDepartamento(departamentoRepository.getById(personaDto.getIdDepartamento()));
        return coordinador;
    }
}
