package pl.bw.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bw.charity.domain.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
