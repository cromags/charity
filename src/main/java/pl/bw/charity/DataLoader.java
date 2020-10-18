package pl.bw.charity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bw.charity.domain.model.*;
import pl.bw.charity.domain.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final OrganizationRepository organizationRepository;
    private final DonationRepository donationRepository;
    private final GoodRepository goodRepository;
    private final StatusRepository statusRepository;
    private final DetailsRepository detailsRepository;
    private final CourierRepository courierRepository;

    public DataLoader(OrganizationRepository organizationRepository,
                      DonationRepository donationRepository,
                      GoodRepository goodRepository,
                      StatusRepository statusRepository,
                      DetailsRepository detailsRepository,
                      CourierRepository courierRepository){
        this.organizationRepository = organizationRepository;
        this.donationRepository = donationRepository;
        this.goodRepository = goodRepository;
        this.statusRepository = statusRepository;
        this.detailsRepository = detailsRepository;
        this.courierRepository = courierRepository;
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

        Status[] stats = new Status[]{
                new Status("Złożone"),                  //1
                new Status("Zamówiono kuriera"),        //2
                new Status("Na magazynie"),             //3
                new Status("Wysłano do fundacji"),      //4
                new Status("Dostarczono")               //5
        };

        for (Status s : stats){
            statusRepository.save(s);
        }

        Good[] goods = new Good[]{
                new Good("ubrania"),
                new Good("zabawki"),
                new Good("książki"),
                new Good("inne")
        };

        for(Good g : goods){
            goodRepository.save(g);
        }

        Courier[] couriers = new Courier[]{
                new Courier("CharityTransport", "", "", "", "#aecf00" ),
                new Courier("DHL", "dhl@dhl.pl", "dhl.com", "111-222-333", "#d40511" ),
                new Courier("UPS", "ups@ups.pl", "www.ups.com", "999-888-777", "#f7be00" ),
                new Courier("FedEx", "fedex@fedex.pl", "www.fedex.com", "456-456-654", "#fe6602" )
        };

        for(Courier c : couriers){
            courierRepository.save(c);
        }

        Donation d1 = new Donation(
                "00-001",
                "Warszawa",
                "Marszałkowska 1",
                "666-777-999",
                LocalDate.of(2020,10,01),
                LocalTime.of(12,15),
                "na 15 minut przed proszę dzwonić",
                orgs[0],
                stats[3],
                couriers[3]
        );
        donationRepository.save(d1);
        DonationDetails d1_1 = new DonationDetails(d1, goods[0], 4);
        detailsRepository.save(d1_1);

        Donation d2 = new Donation(
                "99-998",
                "Gdynia",
                "Pucka 3",
                "145-568-200",
                LocalDate.of(2020,10,01),
                LocalTime.of(17,45),
                "",
                orgs[2],
                stats[2],
                couriers[2]
        );
        donationRepository.save(d2);
        DonationDetails d2_1 = new DonationDetails(d2, goods[1], 2);
        DonationDetails d2_2 = new DonationDetails(d2, goods[2], 3);
        detailsRepository.save(d2_1);
        detailsRepository.save(d2_2);

        Donation d3 = new Donation(
                "51-333",
                "Wrocław",
                "Kazimierza Wielkiego 4",
                "456-888-654",
                LocalDate.of(2020,10,03),
                null,
                "",
                orgs[3],
                stats[4],
                couriers[0]
        );
        donationRepository.save(d3);
        DonationDetails d3_1 = new DonationDetails(d3, goods[3], 15);
        detailsRepository.save(d3_1);

        Donation d4 = new Donation(
                "91-111",
                "Łódź",
                "Warszwawska 7",
                "469-865-788",
                LocalDate.of(2020,10,07),
                null,
                "worki proszę odebrać u ochrony",
                orgs[2],
                stats[1],
                couriers[1]
        );
        donationRepository.save(d4);
        DonationDetails d4_1 = new DonationDetails(d4, goods[1], 4);
        DonationDetails d4_2 = new DonationDetails(d4, goods[3], 2);
        detailsRepository.save(d4_1);
        detailsRepository.save(d4_2);


        System.out.println("elo");

    }
}
