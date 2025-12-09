package co.edu.unicauca.users_ms.controller;


import co.edu.unicauca.users_ms.entity.*;
import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import co.edu.unicauca.users_ms.infra.dto.PersonaRegistrarDto;
import co.edu.unicauca.users_ms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/personas")
public class PersonaController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/iniciarSesion/{username}/{password}")
    public ResponseEntity<?> iniciarSesion(@PathVariable String username,@PathVariable String password) throws Exception {

        if(loginService.iniciarSesion(username,password)==null)
        {
            return new ResponseEntity<>("usuario o contraseña incorrectos",HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(loginService.iniciarSesion(username,password),HttpStatus.OK);
    }
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody PersonaRegistrarDto personaDto) throws Exception {
        PersonaDto persona= registerService.registrarPersona(personaDto);
        if(persona==null)
            return new ResponseEntity<>("persona no existe",HttpStatus.BAD_REQUEST);
        System.out.println("Persona registrada");
        return new ResponseEntity<>(persona,HttpStatus.OK);
    }

}
