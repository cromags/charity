package pl.bw.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bw.charity.domain.model.Donation;
import pl.bw.charity.domain.model.DonationDetails;
import pl.bw.charity.domain.model.Good;
import pl.bw.charity.domain.model.Organization;
import pl.bw.charity.service.GoodService;
import pl.bw.charity.service.OrganizationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DonationController {

    private final GoodService goodService;
    private final OrganizationService organizationService;

    public DonationController(GoodService goodService,
                              OrganizationService organizationService) {
        this.goodService = goodService;
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/step1")
    public String step1(Model model) {
        model.addAttribute("goods", goodService.getAllGoods());
        model.addAttribute("dd", new DonationDetails());
        return "step1";
    }

    @RequestMapping(value = "/step2")
    public String step2(Model model,
                        DonationDetails donationdetails,
                        HttpServletRequest request) {

        HttpSession session = request.getSession();
        Good good = donationdetails.getGood();
        Integer quantity = donationdetails.getQuantity();
        session.setAttribute("good", good);
        session.setAttribute("quantity", quantity);

        model.addAttribute("orgs", organizationService.findAll());
        model.addAttribute("donation", new Donation());
        return "step2";
    }

    @RequestMapping(value = "/step3")
    public String step3(Model model,
                        Donation donation,
                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        Organization organization = donation.getOrganization();
        session.setAttribute("organization", organization);

        model.addAttribute(donation);
        return "step3";
    }

    @RequestMapping(value = "/summary")
    public String summary(Model model,
                          Donation donation,
                          HttpServletRequest request) {

        HttpSession session = request.getSession();
        Good good = (Good) session.getAttribute("good");
        Integer quantity = (Integer) session.getAttribute("quantity");
        Organization organization = (Organization) session.getAttribute("organization");


        model.addAttribute("donation", donation);
        model.addAttribute("good", good);
        model.addAttribute("organization", organization);
        model.addAttribute("quantity", quantity);


        return "summary";
    }



}
