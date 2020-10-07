package pl.bw.charity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bw.charity.domain.model.Organization;
import pl.bw.charity.domain.repository.OrganizationRepository;

@Component
public class MyEntityTester implements CommandLineRunner {

    private final OrganizationRepository organizationRepository;

    public MyEntityTester(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("elo 1");
        Organization org = new Organization("xxx","descyyy");
        organizationRepository.save(org);
        System.out.println("elo 2");

    }
}
