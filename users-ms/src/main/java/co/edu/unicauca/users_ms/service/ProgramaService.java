package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Programa;
import co.edu.unicauca.users_ms.repository.ProgramaDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramaService implements BaseService<Programa, Integer> {

    @Autowired
    private ProgramaDomainRepository repository;

    @Override
    public List<Programa> findAll() {
        return repository.findAll();
    }

    @Override
    public Programa findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Programa save(Programa programa) {
        return repository.save(programa);
    }

    @Override
    public Programa updateById(Programa programa) {
        return repository.save(programa);
    }

    @Override
    public boolean deleteById(Integer id) {
        return repository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
}

