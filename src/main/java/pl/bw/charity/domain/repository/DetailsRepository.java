package pl.bw.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.bw.charity.domain.model.DonationDetails;

import java.util.List;

public interface DetailsRepository extends JpaRepository<DonationDetails, Long> {

    @Query("select sum(dd.quantity) from DonationDetails dd group by dd.donation.id")
    List<Integer> getBagsForEachDonation();

}
