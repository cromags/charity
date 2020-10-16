package pl.bw.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.charity.domain.model.Organization;
import pl.bw.charity.domain.repository.DetailsRepository;
import pl.bw.charity.domain.repository.DonationRepository;
import pl.bw.charity.domain.repository.OrganizationRepository;
import pl.bw.charity.service.DonationDetailsService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrganizationRepository organizationRepository;
    private final DonationRepository donationRepository;
    private final DetailsRepository detailsRepository;


    public AdminController(OrganizationRepository organizationRepository,
                           DonationRepository donationRepository,
                           DetailsRepository detailsRepository) {
        this.organizationRepository = organizationRepository;
        this.donationRepository = donationRepository;
        this.detailsRepository = detailsRepository;
    }

    @RequestMapping(value = "/organizations")
    public String orgsPage(Model model) {
        model.addAttribute("orgs", organizationRepository.findAll());
        return "admin/orgs";
    }

    @RequestMapping(value = "/deleteOrg/{id}")
    public String delOrg(@PathVariable Long id, Model model) {

        Optional<Organization> from = organizationRepository.findById(id);
        Organization org = from.orElse(null);
        if (org == null) {
            return "err/notfound";
        }
        organizationRepository.delete(org);
        return "redirect:/";
    }

    @RequestMapping(value = "/addOrg")
    public String addOrg(Model model) {
        model.addAttribute("org", new Organization());
        return "admin/addOrg";
    }

    @RequestMapping(value = "/doAddOrg", method = RequestMethod.POST)
    public String doAddOrg(@Valid Organization organization, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/addOrg";
        }
        organizationRepository.save(organization);
        return "redirect:/index";
    }

    @RequestMapping(value = "/donations")
    public String donationsPage(Model model) {
        model.addAttribute("donations", donationRepository.findAll());





        return "admin/donations";
    }
}
