package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.entities.Estudiante;
import co.edu.unicauca.administracionDocumental_ms.entities.ProyectoDeGrado;
import co.edu.unicauca.administracionDocumental_ms.entities.Profesor;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.FormatoARequest;
import co.edu.unicauca.administracionDocumental_ms.repository.EstudianteRepository;
import co.edu.unicauca.administracionDocumental_ms.repository.FormatoARepository;
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
    private ProfesorRepository profesorRepo;

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private FormatoARepository formatoRepo;

    @Override
    @Transactional
    public FormatoARequest crearProyectoInvestigacion(FormatoARequest req) throws Exception {
        try {
            Profesor prof = profesorRepo.findByCorreoElectronico(req.getCorreoDirector()).orElseThrow(() -> new Exception("Director no encontrado"));

            Estudiante est1 = estudianteRepo.findByCorreoElectronico(req.getCorreoEstudiante1()).orElseThrow(() -> new Exception("Estudiante 1 no encontrado"));

            List<Profesor> codirectores = null;
            if (req.getCorreoCodirectores() != null && !req.getCorreoCodirectores().isEmpty()) {
                codirectores = new ArrayList<>();
                for (String correo : req.getCorreoCodirectores()) {
                    Profesor codirector = profesorRepo.findByCorreoElectronico(correo)
                            .orElseThrow(() -> new Exception("Codirector no encontrado: " + correo));
                    codirectores.add(codirector);
                }
            }

            Estudiante est2 = null;
            if (req.getCorreoEstudiante2() != null) {
                est2 = estudianteRepo.findByCorreoElectronico(req.getCorreoEstudiante2()).orElseThrow(() -> new Exception("Estudiante 2 no encontrado"));
            }

            ProyectoDeGrado formato = prof.iniciarProyectoDeGradoInvestigacion(
                    req.getTitulo(),
                    req.getObjetivo(),
                    req.getObjetivoEspecifico(),
                    req.getArchivoAdjunto(),
                    est1,
                    est2,
                    codirectores
            );

            formatoRepo.save(formato);

            return construirRespuesta(formato, est1, est2);

        } catch (Exception ex) {
            throw new Exception("Error al crear proyecto de investigación: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public FormatoARequest crearProyectoPractica(FormatoARequest req) throws Exception {
        try {
            Profesor prof = profesorRepo.findByCorreoElectronico(req.getCorreoDirector()).orElseThrow(() -> new Exception("Director no encontrado"));

            Estudiante est1 = estudianteRepo.findByCorreoElectronico(req.getCorreoEstudiante1()).orElseThrow(() -> new Exception("Estudiante 1 no encontrado"));

            List<Profesor> codirectores = null;
            if (req.getCorreoCodirectores() != null && !req.getCorreoCodirectores().isEmpty()) {
                codirectores = new ArrayList<>();
                for (String correo : req.getCorreoCodirectores()) {
                    Profesor codirector = profesorRepo.findByCorreoElectronico(correo)
                            .orElseThrow(() -> new Exception("Codirector no encontrado: " + correo));
                    codirectores.add(codirector);
                }
            }

            Estudiante est2 = null;
            if (req.getCorreoEstudiante2() != null) {
                throw new Exception("En el proyecto de practica solo puede haber un estudiante");
            }

            ProyectoDeGrado formato = prof.iniciarProyectoDeGradoPracticaLaboral(
                    req.getTitulo(),
                    req.getObjetivo(),
                    req.getObjetivoEspecifico(),
                    req.getArchivoAdjunto(),
                    est1,
                    est2,
                    codirectores
            );

            formatoRepo.save(formato);

            return construirRespuesta(formato, est1, est2);

        } catch (Exception ex) {
            throw new Exception("Error al crear proyecto de investigación: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public FormatoARequest findById(Long id) throws Exception {
        try {
            ProyectoDeGrado proyecto = formatoRepo.findById(id).orElseThrow(() -> new Exception("Proyecto no encontrado"));

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
    public List<FormatoARequest> findAll() throws Exception {
        try {
            List<ProyectoDeGrado> proyectos = formatoRepo.findAll();
            return proyectos.stream().map(proyectoDeGrado -> construirRespuesta(proyectoDeGrado, proyectoDeGrado.getEstudiante1(), proyectoDeGrado.getEstudiante2())).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new Exception("Error al listar proyectos: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public FormatoARequest updateById(Long id, FormatoARequest req) throws Exception {
        try {
            ProyectoDeGrado proyecto = formatoRepo.findById(id).orElseThrow(() -> new Exception("Proyecto no encontrado"));

            proyecto.setTitulo(req.getTitulo());
            proyecto.setObjetivo(req.getObjetivo());
            proyecto.setObjetivoEspecifico(req.getObjetivoEspecifico());
            proyecto.setArchivoAdjunto(req.getArchivoAdjunto());

            formatoRepo.save(proyecto);

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
            if (!formatoRepo.existsById(id)) {
                throw new Exception("Proyecto no encontrado");
            }
            formatoRepo.deleteById(id);
            return true;
        } catch (Exception ex) {
            throw new Exception("Error al eliminar proyecto: " + ex.getMessage());
        }
    }

    private FormatoARequest construirRespuesta(ProyectoDeGrado formato, Estudiante est1, Estudiante est2) {
        FormatoARequest respuesta = new FormatoARequest();

        respuesta.setTitulo(formato.getTitulo());
        respuesta.setObjetivo(formato.getObjetivo());
        respuesta.setObjetivoEspecifico(formato.getObjetivoEspecifico());
        respuesta.setArchivoAdjunto(formato.getArchivoAdjunto());
        respuesta.setCorreoDirector(formato.getDirector().getCorreoElectronico());

        List<String> listaCodirectores = new ArrayList<>();
        if (formato.getCodirectores() != null) {
            for (Profesor p : formato.getCodirectores()) {
                listaCodirectores.add(p.getCorreoElectronico());
            }
        }
        respuesta.setCorreoCodirectores(listaCodirectores);
        respuesta.setCorreoEstudiante1(est1.getCorreoElectronico());

        if (est2 != null) {
            respuesta.setCorreoEstudiante2(est2.getCorreoElectronico());
        }

        return respuesta;
    }
}