package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.infra.dto.FormatoARequest;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IProyectoService {

    @Transactional
    FormatoARequest crearProyectoInvestigacion(FormatoARequest req) throws Exception;
    @Transactional
    FormatoARequest crearProyectoPractica(FormatoARequest req) throws Exception;
    @Transactional
    FormatoARequest findById(Long id) throws Exception;
    @Transactional
    List<FormatoARequest> findAll() throws Exception;
    @Transactional
    FormatoARequest updateById(Long id, FormatoARequest req) throws Exception;
    @Transactional
    boolean deleteById(Long id) throws Exception;


}