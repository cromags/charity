package pl.bw.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.charity.domain.model.DonationDetails;

public interface DetailsRepository extends JpaRepository<DonationDetails, Long> {
}
