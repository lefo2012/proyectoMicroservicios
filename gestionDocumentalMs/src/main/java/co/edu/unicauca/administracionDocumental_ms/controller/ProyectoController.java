package co.edu.unicauca.administracionDocumental_ms.controller;

import co.edu.unicauca.administracionDocumental_ms.entities.Coordinador;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoDto;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoRequest;
import co.edu.unicauca.administracionDocumental_ms.repository.ProyectoReposiroty;
import co.edu.unicauca.administracionDocumental_ms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private CoordinadorService coordinadorService;
    @Autowired
    private ProyectoReposiroty proyectoReposiroty;
    @Autowired
    private JefeDepService jefeDepService;

    @PostMapping("/investigacion")
    public ResponseEntity<?> subirFormatoInvestigacion(@RequestBody ProyectoRequest req) {
        try {
            ProyectoRequest formatoCreado = proyectoService.crearProyectoInvestigacion(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(formatoCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al crear proyecto: " + e.getMessage()));
        }
    }

    @PostMapping("/practica")
    public ResponseEntity<?> subirFormatoPractica(@RequestBody ProyectoRequest req) {
        try {
            ProyectoRequest formatoCreado = proyectoService.crearProyectoPractica(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(formatoCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al crear proyecto: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProyecto(@PathVariable Long id) {
        try {
            ProyectoRequest proyecto = proyectoService.findById(id);
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Error al buscar proyecto: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosProyectos() {
        try {
            List<ProyectoRequest> proyectos = proyectoService.findAll();
            return ResponseEntity.ok(proyectos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al listar proyectos: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProyecto(@PathVariable Long id, @RequestBody ProyectoRequest req) {
        try {
            ProyectoRequest proyectoActualizado = proyectoService.updateById(id, req);
            return ResponseEntity.ok(proyectoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error al actualizar proyecto: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProyecto(@PathVariable Long id) {
        try {
            proyectoService.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Proyecto eliminado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Error al eliminar proyecto: " + e.getMessage()));
        }
    }

    @GetMapping ("/getProyectos/estudiante/{correoElectronico}")
    public ResponseEntity<?> obtenerProyectosEstudiante(@PathVariable String correoElectronico) {
        try{
            List<ProyectoDto> proyectos = estudianteService.listaProyecto(correoElectronico);
            return ResponseEntity.ok(proyectos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al listar proyectos: " + e.getMessage()));
        }
    }
    @GetMapping("/getProyectos/profesor/{correoElectronico}")
    public ResponseEntity<?> obtenerProyectosProfesor(@PathVariable String correoElectronico) {
        try{
            List<ProyectoDto> proyectos = profesorService.listaProyecto(correoElectronico);
            return ResponseEntity.ok(proyectos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al listar proyectos: " + e.getMessage()));
        }
    }
    @GetMapping ("/getProyectos/coordinador/{correoElectronico}")
    public ResponseEntity<?> obtenerProyectosCoordinador(@PathVariable String correoElectronico) {
        try{
            List<ProyectoDto> proyectos = coordinadorService.listaProyecto(correoElectronico);
            return ResponseEntity.ok(proyectos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al listar proyectos: " + e.getMessage()));
        }
    }
    @GetMapping ("/getProyectos/jefeDepartamento/{correoElectronico}")
    public ResponseEntity<?> obtenerProyectosJefeDepartamento(@PathVariable String correoElectronico) {
        try{
            List<ProyectoDto> proyectos = jefeDepService.listaProyecto(correoElectronico);
            return ResponseEntity.ok(proyectos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al listar proyectos: " + e.getMessage()));
        }
    }

    @GetMapping("/calificarProyecto/aprobar/{idProyecto}/{correoElectronico}/{fecha}")
    public ResponseEntity<?> aprobarProyecto(@PathVariable long idProyecto, @PathVariable String correoElectronico, @PathVariable String fecha)
    {
        try
        {
            Coordinador coordinador = coordinadorService.findById(correoElectronico);
            proyectoReposiroty.save(coordinadorService.aprobarFormatoA(proyectoReposiroty.findById(idProyecto).get(),coordinador));
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/calificarProyecto/rechazar/{idProyecto}/{correoElectronico}/{fecha}")
    public ResponseEntity<String> rechazarProyecto(@PathVariable long idProyecto, @PathVariable String correoElectronico, @PathVariable String fecha)
    {
        try
        {
            Coordinador coordinador = coordinadorService.findById(correoElectronico);
            proyectoReposiroty.save(coordinadorService.rechazarFormatoA(proyectoReposiroty.findById(idProyecto).get(),coordinador));
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}