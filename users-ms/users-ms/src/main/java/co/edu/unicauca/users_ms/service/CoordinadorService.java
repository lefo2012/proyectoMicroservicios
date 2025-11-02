package co.edu.unicauca.users_ms.service;

import co.edu.unicauca.users_ms.entity.Coordinador;
import co.edu.unicauca.users_ms.repository.CoordinadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinadorService implements BaseService<Coordinador,String>{

    @Autowired
    CoordinadorRepository coordinadorRepository;

    @Override
    @Transactional
    public List<Coordinador> findAll() throws Exception {
        return List.of();
    }

    @Override
    @Transactional
    public Coordinador findById(String id) throws Exception {
        try {
            System.out.println("Estoy entrando a coordinador finById id:"+id);
            Optional<Coordinador> coordinador = coordinadorRepository.findByCorreoElectronico(id);

            System.out.println(coordinador);
            return coordinador.orElse(null);
        }catch(Exception ex){
            throw new Exception("Error al buscar el coordinador con id: "+id+" :"+ex.getMessage());
        }

    }

    @Override
    @Transactional
    public Coordinador save(Coordinador entity) throws Exception {
        try{
            return coordinadorRepository.save(entity);
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar coordinador: "+ex.getMessage());
        }
    }

    @Override
    @Transactional
    public Coordinador updateById(Coordinador entity) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteById(String id) throws Exception {
        return false;
    }
}
