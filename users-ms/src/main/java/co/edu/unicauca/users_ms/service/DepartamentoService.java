package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Departamento;

import co.edu.unicauca.users_ms.repository.DepartamentoDomainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartamentoService implements BaseService<Departamento,Integer>{

    @Autowired
    private DepartamentoDomainRepository repository;

    @Override
    public List<Departamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Departamento findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Departamento save(Departamento d) {
        return repository.save(d);
    }

    @Override
    public Departamento updateById(Departamento d) {
        return repository.save(d);
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
