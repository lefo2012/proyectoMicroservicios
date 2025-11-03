package co.edu.unicauca.users_ms.repository;

import co.edu.unicauca.users_ms.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
    Optional<Estudiante> findByCorreoElectronico(String correoElectronico);

}
