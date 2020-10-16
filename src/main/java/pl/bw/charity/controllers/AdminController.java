package pl.bw.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bw.charity.domain.model.Organization;
import pl.bw.charity.domain.repository.OrganizationRepository;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrganizationRepository organizationRepository;

    public AdminController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
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
}