package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.infra.dto.ProyectoRequest;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IProyectoService {

    @Transactional
    ProyectoRequest crearProyectoInvestigacion(ProyectoRequest req) throws Exception;
    @Transactional
    ProyectoRequest crearProyectoPractica(ProyectoRequest req) throws Exception;
    @Transactional
    ProyectoRequest findById(Long id) throws Exception;
    @Transactional
    List<ProyectoRequest> findAll() throws Exception;
    @Transactional
    ProyectoRequest updateById(Long id, ProyectoRequest req) throws Exception;


    boolean deleteById(Long id) throws Exception;


}