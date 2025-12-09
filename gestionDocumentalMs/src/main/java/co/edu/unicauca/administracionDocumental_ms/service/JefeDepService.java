package co.edu.unicauca.administracionDocumental_ms.service;


import co.edu.unicauca.administracionDocumental_ms.entities.*;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.AsignarEvaluadoresRequest;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.NotificationRequest;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.infra.rabbitConfig.NotificationProducer;
import co.edu.unicauca.administracionDocumental_ms.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JefeDepService implements  BaseService <JefeDepartamento,String>{
    @Autowired
    private AnteProyectoDomainRepository anteProyectoRepository;
    @Autowired
    private JefeDepartamentoDomainRepository JefeDepRepository;
    @Autowired
    private ProfesorDomainRepository profesorRepository;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private ProyectoDomainReposiroty proyectoRepository;
    @Autowired
    private DepartamentoDomainRepository departamentoRepository;
    @Autowired
    private NotificationProducer notificationProducer;
    @Override
    @Transactional
    public List<JefeDepartamento> findAll() throws Exception {
        try{
            return JefeDepRepository.findAll();
        }catch (Exception ex)
        {
            throw new Exception("Error al buscar todos los profesores: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public JefeDepartamento findById(String id) throws Exception {
        try
        {
            Optional<JefeDepartamento> profesor = JefeDepRepository.findByCorreo(id);
            System.out.println(profesor);
            return profesor.orElse(null);
        }catch(Exception ex)
        {
            throw new Exception("Error al buscar el profesor con id: "+id+" :"+ex.getMessage());
        }

    }

    @Override
    @Transactional
    public JefeDepartamento save(JefeDepartamento entity) throws Exception {
        try{
            return JefeDepRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar profesor: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public JefeDepartamento updateById(JefeDepartamento entity) throws Exception {
        try {
            return JefeDepRepository.save(entity);
        }catch (Exception ex){
            throw new Exception("Error al guardar profesor: "+ex.getMessage());
        }

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
            Optional<JefeDepartamento> jefeDepartamentoR = JefeDepRepository.findByCorreo(correoElectronico);
            JefeDepartamento jefeDepartamento = jefeDepartamentoR.orElse(null);
            if(jefeDepartamento != null)
            {
                listaProyectos = new ArrayList<>();
                for(ProyectoDeGrado proyecto : jefeDepartamento.getProyectosDeGrado())
                {
                    listaProyectos.add(proyectoService.mapearProyecto(proyecto));
                }
                return listaProyectos;
            }else
            {
                throw new Exception("Jefe de departamento no encontrado: "+correoElectronico);
            }

        }catch(Exception ex){
            throw new Exception("Error al listar proyectos de grado: "+ex.getMessage());
        }
    }
    public JefeDepartamento mapearDto(PersonaDto personaDto) throws Exception {
        JefeDepartamento jefeDepartamento = new JefeDepartamento();
        jefeDepartamento.setNombre(personaDto.getNombre());
        jefeDepartamento.setApellido(personaDto.getApellido());
        jefeDepartamento.setCelular(personaDto.getCelular());
        jefeDepartamento.setCorreoElectronico(personaDto.getCorreoElectronico());
        jefeDepartamento.setId(personaDto.getId());
        jefeDepartamento.setDepartamento(departamentoRepository.findById(personaDto.getIdDepartamento()).orElseThrow(() -> new Exception("Departamento no encontrado")));
        return jefeDepartamento;
    }


    public AnteProyecto asignarEvaluadores(AsignarEvaluadoresRequest asignarEvaluadoresRequest) throws Exception {
        try{
            AnteProyecto  anteProyecto = anteProyectoRepository.findById(asignarEvaluadoresRequest.getIdProyecto()).orElseThrow(() -> new RuntimeException("AnteProyecto no encontrado"));
            Profesor evaluador1 = profesorRepository.findByCorreo(asignarEvaluadoresRequest.getCorreoElectronicoEvaluador1()).orElseThrow(() -> new RuntimeException("Evaluador 1 no encontrado"));
            Profesor evaluador2 = profesorRepository.findByCorreo(asignarEvaluadoresRequest.getCorreoElectronicoEvaluador2()).orElseThrow(() -> new RuntimeException("Evaluador 2 no encontrado"));
            System.out.println("Evaluador 1: "+evaluador1.getDepartamento().getJefeDepartamento().getId());
            System.out.println("Evaluador 1: "+evaluador1.getDepartamento().getJefeDepartamento().getNombre());
            if (evaluador1==evaluador2)
            {
                throw new Exception("Los dos evaluadores no pueden ser el mismo profesor");
            }

            anteProyecto.setEvaluador1(evaluador1);
            anteProyecto.setEvaluador2(evaluador2);
            proyectoRepository.save(anteProyecto.getProyectoDeGrado().getJefeDepartamento().asignagEvaluadores(anteProyecto.getProyectoDeGrado()));
            enviarNotificacionEvaluador(anteProyecto);
            return anteProyectoRepository.save(anteProyecto);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al asignar evaluadores"+e.getMessage());
        }
    }

    private void enviarNotificacionEvaluador(AnteProyecto anteproyecto) {
        NotificationRequest notification = new NotificationRequest();
        List<String> emails = new ArrayList<>();

        Profesor evaluador1 = anteproyecto.getEvaluador1();
        if (evaluador1 != null && evaluador1.getCorreoElectronico() != null)
            emails.add(evaluador1.getCorreoElectronico());

        Profesor evaluador2 = anteproyecto.getEvaluador2();
        if (evaluador2 != null && evaluador2.getCorreoElectronico() != null)
            emails.add(evaluador2.getCorreoElectronico());

        ProyectoDeGrado proyectoDeGrado = anteproyecto.getProyectoDeGrado();
        notification.setEmail(emails);
        notification.setSubject("Asignacion como evaluador de Proyecto de Grado ");
        notification.setMessage(
                "Ha/n sido asignado/s como evaluador/es del Proyecto de Grado Titulado: "+proyectoDeGrado.getTitulo()+"\n\n" +
                        "Saludos,\nSistema de Proyectos"
        );

        try {
            notificationProducer.enviarNotificacion(notification);
        } catch (Exception e) {
            System.err.println(" Error al enviar notificación de asignacion de evaluadores: " + e.getMessage());
        }
    }
}
