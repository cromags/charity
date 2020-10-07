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

        System.out.println("elo 1");

        Organization org1 = new Organization("Nadzieja Stowarzyszenie Osób Bezdomnych","Dom dla samotnych kobiet z dziećmi, rodzin");
        Organization org2 = new Organization("Schronisko św. Brata Alberta","Pomoc dla bezdomnych mężczyzn.");

        organizationRepository.save(org1);
        organizationRepository.save(org2);

        Donation d1 = new Donation("00-001",
                "Warszawa",
                "Marszałkowska 1",
                "666-777-999",
                LocalDate.of(2020,06,06),
                LocalTime.of(12,15),
                "na 15 minut przed proszę dzwonić",
                org1
                );
        donationRepository.save(d1);

        Good clothes = new Good("ubrania");
        Good toys = new Good("zabawki");
        goodRepository.save(clothes);
        goodRepository.save(toys);


        System.out.println("elo 2");

    }
}
