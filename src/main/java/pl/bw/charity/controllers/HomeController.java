package pl.bw.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bw.charity.service.DonationDetailsService;
import pl.bw.charity.service.OrganizationService;


@Controller
public class HomeController {

    private final OrganizationService organizationService;
    private final DonationDetailsService donationDetailsService;

    public HomeController(OrganizationService organizationService,
                          DonationDetailsService donationDetailsService) {
        this.organizationService = organizationService;
        this.donationDetailsService = donationDetailsService;
    }

    @RequestMapping({"/", "/index"})
    public String homeAction(Model model){

        model.addAttribute("orgs", organizationService.findAll());
        model.addAttribute("bags", donationDetailsService.getNumberOfBags());
        model.addAttribute("donations", donationDetailsService.getNumberOfDonations());
        return "index";
    }
}
