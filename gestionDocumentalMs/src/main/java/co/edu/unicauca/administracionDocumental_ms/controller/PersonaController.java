package co.edu.unicauca.administracionDocumental_ms.controller;

import co.edu.unicauca.administracionDocumental_ms.entities.Persona;
import co.edu.unicauca.administracionDocumental_ms.infra.dto.PersonaDto;
import co.edu.unicauca.administracionDocumental_ms.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/Persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPersona(@RequestBody PersonaDto persona)
    {
        try{
            System.out.println(personaService.guardar(personaService.mapearDto(persona)));
            return ResponseEntity.status(HttpStatus.CREATED).body(persona);
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Error al guardar persona: " + e.getMessage()));
        }


    }
}
