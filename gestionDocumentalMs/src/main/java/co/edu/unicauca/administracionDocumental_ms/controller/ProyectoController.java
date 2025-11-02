package co.edu.unicauca.administracionDocumental_ms.controller;

import co.edu.unicauca.administracionDocumental_ms.infra.dto.FormatoARequest;
import co.edu.unicauca.administracionDocumental_ms.service.ProyectoService;
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

    @PostMapping("/investigacion")
    public ResponseEntity<?> subirFormatoInvestigacion(@RequestBody FormatoARequest req) {
        try {
            FormatoARequest formatoCreado = proyectoService.crearProyectoInvestigacion(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(formatoCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al crear proyecto: " + e.getMessage()));
        }
    }

    @PostMapping("/practica")
    public ResponseEntity<?> subirFormatoPractica(@RequestBody FormatoARequest req) {
        try {
            FormatoARequest formatoCreado = proyectoService.crearProyectoPractica(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(formatoCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al crear proyecto: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProyecto(@PathVariable Long id) {
        try {
            FormatoARequest proyecto = proyectoService.findById(id);
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Error al buscar proyecto: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosProyectos() {
        try {
            List<FormatoARequest> proyectos = proyectoService.findAll();
            return ResponseEntity.ok(proyectos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al listar proyectos: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProyecto(@PathVariable Long id, @RequestBody FormatoARequest req) {
        try {
            FormatoARequest proyectoActualizado = proyectoService.updateById(id, req);
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

}