package pl.bw.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.charity.domain.model.Courier;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}
