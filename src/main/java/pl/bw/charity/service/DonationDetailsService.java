package pl.bw.charity.service;

import org.springframework.stereotype.Service;
import pl.bw.charity.domain.model.DonationDetails;
import pl.bw.charity.domain.repository.DetailsRepository;
import pl.bw.charity.domain.repository.DonationRepository;

@Service
public class DonationDetailsService {

    private final DetailsRepository detailsRepository;

    public DonationDetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public Integer getNumberOfBags(){
        Integer bags = 0;
        for(DonationDetails d: detailsRepository.findAll()){
            bags += d.getQuantity();
        }
        return bags;
    }

    public Integer getNumberOfDonations(){
        return detailsRepository.findAll().size();
    }

    public DetailsRepository getDetailsRepository(){
        return detailsRepository;
    }
}
