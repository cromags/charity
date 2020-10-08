package pl.bw.charity.service;

import org.springframework.stereotype.Service;
import pl.bw.charity.domain.model.Organization;
import pl.bw.charity.domain.repository.OrganizationRepository;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> findAll(){
        return organizationRepository.findAll();
    }




}
