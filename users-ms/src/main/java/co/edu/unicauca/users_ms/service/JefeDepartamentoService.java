package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.JefeDepartamento;
import co.edu.unicauca.users_ms.repository.JefeDepartamentoDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JefeDepartamentoService implements BaseService<JefeDepartamento, Long> {

    @Autowired
    private JefeDepartamentoDomainRepository repository;

    @Override
    public List<JefeDepartamento> findAll() {
        return repository.findAll();
    }

    @Override
    public JefeDepartamento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public JefeDepartamento findByCorreo(String correo) {
        return repository.findByCorreo(correo).orElse(null);
    }

    public boolean existsByCorreo(String correo) {
        return repository.existsByCorreo(correo);
    }

    @Override
    public JefeDepartamento save(JefeDepartamento jefe) {
        return repository.save(jefe);
    }

    @Override
    public JefeDepartamento updateById(JefeDepartamento jefe) {
        return repository.save(jefe);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}

