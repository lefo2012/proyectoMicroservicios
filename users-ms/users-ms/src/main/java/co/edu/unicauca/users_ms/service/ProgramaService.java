package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Programa;
import co.edu.unicauca.users_ms.repository.ProgramaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaService implements BaseService<Programa, Integer> {

    @Autowired
    private ProgramaRepository programaRepository;

    @Override
    @Transactional
    public List<Programa> findAll() throws Exception {
        try {
            return programaRepository.findAll();
        } catch (Exception ex) {
            throw new Exception("Error al buscar todos los programas: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Programa findById(Integer id) throws Exception {
        try {
            System.out.println("Entrando a findById programa con id: " + id);
            Optional<Programa> programa = programaRepository.findById(id);
            return programa.orElse(null);
        } catch (Exception ex) {
            throw new Exception("Error al buscar el programa con id: " + id + " - " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Programa save(Programa entity) throws Exception {
        try {
            return programaRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar programa: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Programa updateById(Programa entity) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) throws Exception {
        return  false;
    }
}
