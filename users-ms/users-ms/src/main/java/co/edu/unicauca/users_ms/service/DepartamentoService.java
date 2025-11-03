package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Departamento;
import co.edu.unicauca.users_ms.repository.DepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService implements BaseService<Departamento,Integer>{

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    @Transactional
    public List<Departamento> findAll() throws Exception {
        try {
            return departamentoRepository.findAll();
        } catch (Exception ex) {
            throw new Exception("Error al buscar todos los departamentos: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Departamento findById(Integer id) throws Exception {
        try {
            System.out.println("Entrando a findById departamento con id: " + id);
            Optional<Departamento> Departamento = departamentoRepository.findById(id);
            return Departamento.orElse(null);
        } catch (Exception ex) {
            throw new Exception("Error al buscar el departamento con id: " + id + " - " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Departamento save(Departamento entity) throws Exception {
        try {
            return departamentoRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar departamento: " + ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Departamento updateById(Departamento entity) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) throws Exception {
        return  false;
    }
}
