package co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa;


import co.edu.unicauca.administracionDocumental_ms.infra.jpa.ProyectoDeGradoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoDeGradoJpaRepository extends JpaRepository<ProyectoDeGradoJpa, Long> {

}
