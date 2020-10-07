package pl.bw.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.charity.domain.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
