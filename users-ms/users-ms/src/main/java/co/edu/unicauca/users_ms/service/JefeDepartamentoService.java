package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.JefeDepartamento;
import co.edu.unicauca.users_ms.repository.JefeDepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JefeDepartamentoService implements BaseService<JefeDepartamento,String>
{
    @Autowired
    JefeDepartamentoRepository jefeDepartamentoRepository;

    @Override
    @Transactional
    public List<JefeDepartamento> findAll() throws Exception {
        return List.of();
    }

    @Override
    @Transactional
    public JefeDepartamento findById(String id) throws Exception {
        try{
            System.out.println("Entrando a buscar jefe de departamento finById id: "+ id);
            Optional<JefeDepartamento> jefeDepartamento = jefeDepartamentoRepository.findByCorreoElectronico(id);
            System.out.println(jefeDepartamento);
            return jefeDepartamento.orElse(null);
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar al jefe de departamento con id: "+id+" "+ ex.getMessage());
        }

    }

    @Override
    @Transactional
    public JefeDepartamento save(JefeDepartamento entity) throws Exception {
        try{
            return jefeDepartamentoRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar jefe de departamento: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public JefeDepartamento updateById(JefeDepartamento entity) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(String id) throws Exception {
        return false;
    }
}
