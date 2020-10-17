package pl.bw.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.bw.charity.domain.model.Donation;
import pl.bw.charity.domain.model.DonationDetails;
import pl.bw.charity.domain.model.Organization;
import pl.bw.charity.domain.repository.DetailsRepository;
import pl.bw.charity.domain.repository.DonationRepository;
import pl.bw.charity.domain.repository.StatusRepository;
import pl.bw.charity.service.GoodService;
import pl.bw.charity.service.OrganizationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class DonationController {

    private final GoodService goodService;
    private final OrganizationService organizationService;
    private final DonationRepository donationRepository;
    private final DetailsRepository detailsRepository;
    private final StatusRepository statusRepository;

    public DonationController(GoodService goodService,
                              OrganizationService organizationService,
                              DonationRepository donationRepository,
                              DetailsRepository detailsRepository,
                              StatusRepository statusRepository) {

        this.goodService = goodService;
        this.organizationService = organizationService;
        this.donationRepository = donationRepository;
        this.detailsRepository = detailsRepository;
        this.statusRepository = statusRepository;
    }

    @RequestMapping(value = "/step1")
    public String step1(Model model) {
        model.addAttribute("goods", goodService.getAllGoods());
        model.addAttribute("dd", new DonationDetails());
        return "step1";
    }

    @RequestMapping(value = "/step2", params = {"again"})
    public ModelAndView step2Back(HttpServletRequest request,
                            DonationDetails donationDetails){

        HttpSession session = request.getSession();
        if (session.getAttribute("detailsList") == null) {
            session.setAttribute("detailsList", new HashSet<DonationDetails>());
        }
        Set<DonationDetails> detailsList = (Set<DonationDetails>) session.getAttribute("detailsList");
        detailsList.add(donationDetails);

        return new ModelAndView("redirect:/user/step1");
    }

    @RequestMapping(value = "/step2", params = {"next"})
    public String step2Forward(Model model,
                               HttpServletRequest request,
                               DonationDetails donationDetails) {

        HttpSession session = request.getSession();
        if (session.getAttribute("detailsList") == null) {
            session.setAttribute("detailsList", new HashSet<DonationDetails>());
        }
        Set<DonationDetails> detailsList = (Set<DonationDetails>) session.getAttribute("detailsList");
        detailsList.add(donationDetails);

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
        Organization organization = (Organization) session.getAttribute("organization");
        Set<DonationDetails> detailsList = (Set<DonationDetails>) session.getAttribute("detailsList");
        session.setAttribute("donation", donation);

        model.addAttribute("donation", donation);
        model.addAttribute("organization", organization);

        int allBags = 0;
        Map<String, Integer> det = new HashMap<>();
        for(DonationDetails dd : detailsList){
            allBags += dd.getQuantity();
            det.put(dd.getGood().getName(), dd.getQuantity());
        }

        model.addAttribute("allBags", allBags);
        model.addAttribute("det", det);

        return "summary";
    }

    @RequestMapping(value = "/thankyou")
    public String tkankyou(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Organization organization = (Organization) session.getAttribute("organization");
        Set<DonationDetails> detailsList = (Set<DonationDetails>) session.getAttribute("detailsList");
        Donation donation = (Donation) session.getAttribute("donation");

        donation.setOrganization(organization);
        //set new donation status to -> placed donation status
        donation.setStatus(statusRepository.findById(1L).orElse(null));
        donationRepository.save(donation);

        for (DonationDetails dd : detailsList){
            dd.setDonation(donation);
            detailsRepository.save(dd);
        }

        session.removeAttribute("donation");
        session.removeAttribute("organization");
        session.removeAttribute("detailsList");


        return "thankyou";
    }

    @RequestMapping(value = {"/step2", "/step3","/summary","/thankyou"}, params = {"cancel"})
    public ModelAndView cancel(HttpServletRequest request){

        HttpSession session = request.getSession();

        session.removeAttribute("donation");
        session.removeAttribute("organization");
        session.removeAttribute("detailsList");

        return new ModelAndView("redirect:/user/step1");
    }



}
