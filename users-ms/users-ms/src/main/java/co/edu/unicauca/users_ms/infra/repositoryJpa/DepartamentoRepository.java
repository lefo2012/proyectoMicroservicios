package co.edu.unicauca.users_ms.infra.repositoryJpa;

import co.edu.unicauca.users_ms.infra.jpa.DepartamentoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoJpa, Integer> {
}
