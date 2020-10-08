package pl.bw.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bw.charity.service.OrganizationService;


@Controller
public class HomeController {

    private final OrganizationService organizationService;

    public HomeController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping({"/", "/index"})
    public String homeAction(Model model){

        model.addAttribute("orgs", organizationService.findAll());
        return "index";
    }
}
