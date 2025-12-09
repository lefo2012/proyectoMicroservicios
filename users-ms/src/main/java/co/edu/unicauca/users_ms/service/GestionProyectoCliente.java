package co.edu.unicauca.users_ms.service;


import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "gestionDocumentalMs", url = "http://localhost:8080/Persona")
public interface GestionProyectoCliente {

    @PostMapping ("/registrar")
    void registrar(@RequestBody PersonaDto persona);
}
