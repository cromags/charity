package pl.bw.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.charity.domain.model.Good;

public interface GoodRepository extends JpaRepository<Good, Long> {
}
