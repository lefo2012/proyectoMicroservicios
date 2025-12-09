package co.edu.unicauca.users_ms.infra.repositoryJpa;

import co.edu.unicauca.users_ms.infra.jpa.FacultadJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultadRepository extends JpaRepository<FacultadJpa, Integer> {
}
