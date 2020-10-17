package pl.bw.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.charity.domain.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
