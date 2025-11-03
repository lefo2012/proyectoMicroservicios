package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.NotificationRequest;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoRequest;
import co.edu.unicauca.administracionDocumental_ms.repository.EstudianteRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.ProyectoReposiroty;
import co.edu.unicauca.administracionDocumental_ms.repository.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ProyectoReposiroty proyectoRepository;

    @Autowired
    private NotificationClient notificationClient;
    @Override
    @Transactional
    public ProyectoRequest crearProyectoInvestigacion(ProyectoRequest req) throws Exception {
        try {
            Profesor prof = profesorRepository.findByCorreoElectronico(req.getCorreoDirector()).orElseThrow(() -> new Exception("Director no encontrado"));

            Estudiante est1 = estudianteRepository.findByCorreoElectronico(req.getCorreoEstudiante1()).orElseThrow(() -> new Exception("Estudiante 1 no encontrado"));

            List<Profesor> codirectores = null;

            
            if (req.getCorreoCodirectores() != null && !req.getCorreoCodirectores().isEmpty()) {
                codirectores = new ArrayList<>();
                for (String correo : req.getCorreoCodirectores()) {
                    if(!correo.isEmpty())
                    {
                        Profesor codirector = profesorRepository.findByCorreoElectronico(correo).orElseThrow(() -> new Exception("Codirector no encontrado: " + correo));
                        codirectores.add(codirector);
                    }
                }
            }

            Estudiante est2 = null;
            if (req.getCorreoEstudiante2() != null &&  !req.getCorreoEstudiante2().isEmpty()) {
                est2 = estudianteRepository.findByCorreoElectronico(req.getCorreoEstudiante2()).orElseThrow(() -> new Exception("Estudiante 2 no encontrado"));
            }

            ProyectoDeGrado proyecto = prof.iniciarProyectoDeGradoInvestigacion(
                    req.getTitulo(),
                    req.getObjetivo(),
                    req.getObjetivoEspecifico(),
                    req.getArchivoAdjunto(),
                    est1,
                    est2,
                    codirectores
            );
            
            NotificationRequest notification = new NotificationRequest();

            List<String> emails = new ArrayList<>();
            emails.add(prof.getCorreoElectronico());
            emails.add(est1.getCorreoElectronico());
            if (est2 != null)
                emails.add(est2.getCorreoElectronico());
            for (Profesor codirector : codirectores) {
                emails.add(codirector.getCorreoElectronico());
            }
            emails.add(proyecto.getCoordinador().getCorreoElectronico());
            notification.setEmail(emails);
            notification.setSubject("Nuevo proyecto de investigación creado: " + req.getTitulo());
            notification.setMessage(
                            "Se ha creado el proyecto de grado:\n\n" +
                            "Título: " + req.getTitulo() + "\n" +
                            "Director: " + prof.getNombre() + "\n" +
                            (est2 != null ? "Estudiantes: " + est1.getNombre() + ", " + est2.getNombre() : "Estudiante: " + est1.getNombre()) + "\n\n" +
                            "Saludos,\nSistema de Proyectos"
            );

            try {
                notificationClient.sendNotification(notification);
            } catch (Exception e) {
                System.err.println("No se pudo enviar la notificación: " + e.getMessage());
            }
            
            proyectoRepository.save(proyecto);

            return construirRespuesta(proyecto, est1, est2);

        } catch (Exception ex) {
            throw new Exception("Error al crear proyecto de investigación: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ProyectoRequest crearProyectoPractica(ProyectoRequest req) throws Exception {
        try {
            Profesor prof = profesorRepository.findByCorreoElectronico(req.getCorreoDirector()).orElseThrow(() -> new Exception("Director no encontrado"));

            Estudiante est1 = estudianteRepository.findByCorreoElectronico(req.getCorreoEstudiante1()).orElseThrow(() -> new Exception("Estudiante 1 no encontrado"));

            List<Profesor> codirectores = null;
            if (req.getCorreoCodirectores() != null && !req.getCorreoCodirectores().isEmpty()) {
                codirectores = new ArrayList<>();
                for (String correo : req.getCorreoCodirectores()) {
                    if(!correo.isEmpty())
                    {
                        Profesor codirector = profesorRepository.findByCorreoElectronico(correo).orElseThrow(() -> new Exception("Codirector no encontrado: " + correo));
                        codirectores.add(codirector);
                    }

                }
            }

            Estudiante est2 = null;
            if (req.getCorreoEstudiante2() != null) {
                throw new Exception("En el proyecto de practica solo puede haber un estudiante");
            }

            ProyectoDeGrado proyecto = prof.iniciarProyectoDeGradoPracticaLaboral(
                    req.getTitulo(),
                    req.getObjetivo(),
                    req.getObjetivoEspecifico(),
                    req.getArchivoAdjunto(),
                    est1,
                    est2,
                    codirectores
            );

            proyectoRepository.save(proyecto);

            return construirRespuesta(proyecto, est1, est2);

        } catch (Exception ex) {
            throw new Exception("Error al crear proyecto de investigación: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ProyectoRequest findById(Long id) throws Exception {
        try {
            ProyectoDeGrado proyecto = proyectoRepository.findById(id).orElseThrow(() -> new Exception("Proyecto no encontrado"));

            return construirRespuesta(
                    proyecto,
                    proyecto.getEstudiante1(),
                    proyecto.getEstudiante2()
            );
        } catch (Exception ex) {
            throw new Exception("Error al buscar proyecto: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public List<ProyectoRequest> findAll() throws Exception {
        try {
            List<ProyectoDeGrado> proyectos = proyectoRepository.findAll();
            return proyectos.stream().map(proyectoDeGrado -> construirRespuesta(proyectoDeGrado, proyectoDeGrado.getEstudiante1(), proyectoDeGrado.getEstudiante2())).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new Exception("Error al listar proyectos: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ProyectoRequest updateById(Long id, ProyectoRequest req) throws Exception {
        try {
            ProyectoDeGrado proyecto = proyectoRepository.findById(id).orElseThrow(() -> new Exception("Proyecto no encontrado"));

            proyecto.setTitulo(req.getTitulo());
            proyecto.setObjetivo(req.getObjetivo());
            proyecto.setObjetivoEspecifico(req.getObjetivoEspecifico());
            proyecto.setArchivoAdjunto(req.getArchivoAdjunto());

            proyectoRepository.save(proyecto);

            return construirRespuesta(
                    proyecto,
                    proyecto.getEstudiante1(),
                    proyecto.getEstudiante2()
            );
        } catch (Exception ex) {
            throw new Exception("Error al actualizar proyecto: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws Exception {
        try {
            if (!proyectoRepository.existsById(id)) {
                throw new Exception("Proyecto no encontrado");
            }
            proyectoRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            throw new Exception("Error al eliminar proyecto: " + ex.getMessage());
        }
    }


    private ProyectoRequest construirRespuesta(ProyectoDeGrado proyecto, Estudiante est1, Estudiante est2) {
        ProyectoRequest respuesta = new ProyectoRequest();

        respuesta.setTitulo(proyecto.getTitulo());
        respuesta.setObjetivo(proyecto.getObjetivo());
        respuesta.setObjetivoEspecifico(proyecto.getObjetivoEspecifico());
        respuesta.setArchivoAdjunto(proyecto.getArchivoAdjunto());
        respuesta.setCorreoDirector(proyecto.getDirector().getCorreoElectronico());

        List<String> listaCodirectores = new ArrayList<>();
        if (proyecto.getCodirectores() != null) {
            for (Profesor codirector : proyecto.getCodirectores()) {
                System.out.println("EL CORREO ES: "+codirector.getCorreoElectronico());
                listaCodirectores.add(codirector.getCorreoElectronico());
            }
        }
        respuesta.setCorreoCodirectores(listaCodirectores);
        System.out.println("REPUESTA: "+respuesta.getCorreoCodirectores());
        respuesta.setCorreoEstudiante1(est1.getCorreoElectronico());

        if (est2 != null) {
            respuesta.setCorreoEstudiante2(est2.getCorreoElectronico());
        }

        return respuesta;
    }
    public ProyectoDto mapearProyecto(ProyectoDeGrado proyectoDeGrado)
    {
        ProyectoDto respuesta = new ProyectoDto();
        respuesta.setId(proyectoDeGrado.getId());
        respuesta.setTitulo(proyectoDeGrado.getTitulo());
        respuesta.setEstado(proyectoDeGrado.getEstado());
        respuesta.setTipoProyecto(proyectoDeGrado.getTipoProyecto().toString());
        respuesta.setObjetivo(proyectoDeGrado.getObjetivo());
        respuesta.setObjetivoEspecifico(proyectoDeGrado.getObjetivoEspecifico());
        respuesta.setArchivoAdjunto(proyectoDeGrado.getArchivoAdjunto());
        respuesta.setFechaSubida(proyectoDeGrado.getFechaSubida().toString());
        respuesta.setNombreEstudiante1(proyectoDeGrado.getEstudiante1().getNombre()+" "+proyectoDeGrado.getEstudiante1().getApellido());
        if(proyectoDeGrado.getEstudiante2()!=null){
            respuesta.setNombreEstudiante2(proyectoDeGrado.getEstudiante2().getNombre() + " "+ proyectoDeGrado.getEstudiante2().getApellido());
            respuesta.setIdEstudiante2(proyectoDeGrado.getEstudiante2().getId());
        }

        respuesta.setNombreDirector(proyectoDeGrado.getDirector().getNombre()+ " " + proyectoDeGrado.getDirector().getApellido());
        for(Profesor p : proyectoDeGrado.getCodirectores())
        {
            String nombre = p.getNombre()+" "+p.getApellido();
            respuesta.addCodirector(nombre, p.getId());
        }
        respuesta.setNombreCoordinador(proyectoDeGrado.getCoordinador().getNombre()+" "+proyectoDeGrado.getCoordinador().getApellido());
        respuesta.setIdEstudiante1(proyectoDeGrado.getEstudiante1().getId());

        respuesta.setDirecretorId(proyectoDeGrado.getDirector().getId());
        respuesta.setCoordinadorId(proyectoDeGrado.getCoordinador().getId());
        return respuesta;
    }
}