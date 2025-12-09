package co.edu.unicauca.administracionDocumental_ms.infra.repositoryJpa;

import co.edu.unicauca.administracionDocumental_ms.infra.jpa.DepartamentoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoJpaRepository extends JpaRepository<DepartamentoJpa, Integer> {
}
