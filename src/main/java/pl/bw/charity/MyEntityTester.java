package pl.bw.charity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bw.charity.domain.model.Donation;
import pl.bw.charity.domain.model.Good;
import pl.bw.charity.domain.model.Organization;
import pl.bw.charity.domain.repository.DonationRepository;
import pl.bw.charity.domain.repository.GoodRepository;
import pl.bw.charity.domain.repository.OrganizationRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class MyEntityTester implements CommandLineRunner {

    private final OrganizationRepository organizationRepository;
    private final DonationRepository donationRepository;
    private final GoodRepository goodRepository;

    public MyEntityTester(OrganizationRepository organizationRepository,
                          DonationRepository donationRepository,
                          GoodRepository goodRepository){
        this.organizationRepository = organizationRepository;
        this.donationRepository = donationRepository;
        this.goodRepository = goodRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Organization[] orgs = new Organization[]{
                new Organization("Nadzieja Stowarzyszenie Osób Bezdomnych","Dom dla samotnych kobiet z dziećmi, rodzin"),
                new Organization("Schronisko św. Brata Alberta","Pomoc dla bezdomnych mężczyzn."),
                new Organization("Dobry duszek","Fundacja niesie pomoc głodnym, biednym i osieroconym dzieciom."),
                new Organization("Dzieci Afryki","Pomoc osieroconym dzieciom i ubogim rodzinom w Afryce.")
        };

        for (Organization o : orgs){
            organizationRepository.save(o);
        }



//        Donation d1 = new Donation("00-001",
//                "Warszawa",
//                "Marszałkowska 1",
//                "666-777-999",
//                LocalDate.of(2020,06,06),
//                LocalTime.of(12,15),
//                "na 15 minut przed proszę dzwonić",
//                org1
//                );
//        donationRepository.save(d1);

        Good[] goods = new Good[]{
          new Good("ubrania"),
          new Good("zabawki"),
          new Good("książki"),
          new Good("inne")
        };

        for(Good g : goods){
            goodRepository.save(g);
        }

        System.out.println("elo");

    }
}
