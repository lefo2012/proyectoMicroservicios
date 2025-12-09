package co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa;

import co.edu.unicauca.administracionDocumental_ms.infra.jpa.EstudianteJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteJpaRepository extends JpaRepository<EstudianteJpa,Long> {

    Optional<EstudianteJpa> findByCorreoElectronico(@Param("correoElectronico") String correoElectronico);

    @Query("""
        SELECT est
        FROM EstudianteJpa est
        LEFT JOIN FETCH est.proyectosDeGrado
        WHERE est.correoElectronico = :correoElectronico
    """)
    Optional<EstudianteJpa> findByCorreoLista(@Param("correoElectronico") String correoElectronico);
}
