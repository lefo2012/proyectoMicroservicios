package co.edu.unicauca.users_ms.repository;

import co.edu.unicauca.users_ms.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor,Long> {

    Optional<Profesor> findByCorreoElectronico(String correoElectronico);

}
