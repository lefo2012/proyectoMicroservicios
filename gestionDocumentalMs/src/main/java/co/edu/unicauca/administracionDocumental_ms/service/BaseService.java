package co.edu.unicauca.administracionDocumental_ms.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService <Entity,Key>{
    public List<Entity> findAll() throws Exception;
    public Entity findById(Key id) throws Exception;
    public Entity save(Entity entity) throws Exception;
    public Entity updateById(Entity entity) throws Exception;
    public boolean deleteById(Key id) throws Exception;
}
