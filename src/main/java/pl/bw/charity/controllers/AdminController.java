package pl.bw.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.bw.charity.domain.model.*;
import pl.bw.charity.domain.repository.*;
import pl.bw.charity.service.DonationDetailsService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrganizationRepository organizationRepository;
    private final DonationRepository donationRepository;
    private final DetailsRepository detailsRepository;
    private final StatusRepository statusRepository;
    private final CourierRepository courierRepository;


    public AdminController(OrganizationRepository organizationRepository,
                           DonationRepository donationRepository,
                           DetailsRepository detailsRepository,
                           StatusRepository statusRepository,
                           CourierRepository courierRepository) {
        this.organizationRepository = organizationRepository;
        this.donationRepository = donationRepository;
        this.detailsRepository = detailsRepository;
        this.statusRepository = statusRepository;
        this.courierRepository = courierRepository;
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
            model.addAttribute("input","takiej fundacji");
            return "err/notfound";
        }

        List<Donation> donations =  donationRepository.findAll();
        for (int i = 0; i < donations.size(); i++){
            if(donations.get(i).getOrganization().getId() == org.getId()){
                model.addAttribute("input"," fundacji " + org.getName() + " ponieważ mamy dla niej nieprzekazane dary!");
                return "err/cannotdelete";
            }
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

    @RequestMapping(value = "/editOrg/{id}")
    public String editOrgPage(@PathVariable Long id, Model model) {
        Optional<Organization> from = organizationRepository.findById(id);
        Organization org = from.orElse(null);
        if (org == null) {
            model.addAttribute("input","takiej organizacji");
            return "err/notfound";
        }
        model.addAttribute("org", org);

        return "admin/editOrg";

    }

    @RequestMapping(value = "/doEditOrg", method = RequestMethod.POST)
    public String doEdit(@Valid Organization organization, BindingResult bindingResult) {
        Optional<Organization> from = organizationRepository.findById(organization.getId());
        Organization o = from.orElse(null);
        if (bindingResult.hasErrors() || o == null) {
            return "admin/editOrg";
        }

        o.setName(organization.getName());
        o.setDescription(organization.getDescription());
        organizationRepository.save(o);
        return "redirect:/admin/organizations";
    }

    @RequestMapping(value = "/donations")
    public String donationsPage(Model model) {
        model.addAttribute("donations", donationRepository.findAll());
        model.addAttribute("donat", new Donation());
        model.addAttribute("bags", detailsRepository.getBagsForEachDonation());
        model.addAttribute("stats", statusRepository.findAll());
        model.addAttribute("couriers", courierRepository.findAll());
        return "admin/donations";
    }

    @RequestMapping(value = "/changeStatus/{id}")
    public String changeStatus(@PathVariable Long id, @ModelAttribute("donat") Donation hereIsNewStatus) {

        //in case where user clicked 'selected' item
        if(hereIsNewStatus.getStatus() == null){
            return "redirect:/admin/donations";
        }

        Optional<Donation> from = donationRepository.findById(id);
        Donation donation = from.orElse(null);
        donation.setStatus(hereIsNewStatus.getStatus());
        donationRepository.save(donation);

        return "redirect:/admin/donations";

    }

    @RequestMapping(value = "/courierAssigning/{id}")
    public String assignCourier(@PathVariable Long id, @ModelAttribute("donat") Donation hereIsACourier) {

        //in case where user clicked 'selected' item
        if(hereIsACourier.getCourier() == null){
            return "redirect:/admin/donations";
        }

        Optional<Donation> from = donationRepository.findById(id);
        Donation donation = from.orElse(null);
        donation.setCourier(hereIsACourier.getCourier());
        donationRepository.save(donation);

        return "redirect:/admin/donations";

    }

    @RequestMapping(value = "/couriers")
    public String couriersPage(Model model) {
        model.addAttribute("couriers", courierRepository.findAll());
        return "admin/couriers";
    }

    @RequestMapping(value = "/deleteCourier/{id}")
    public String delCourier(@PathVariable Long id, Model model) {

        Optional<Courier> from = courierRepository.findById(id);
        Courier courier = from.orElse(null);
        if (courier == null) {
            model.addAttribute("input","takiego kuriera");
            return "err/notfound";
        }

        List<Donation> donations =  donationRepository.findAll();
        for (int i = 0; i < donations.size(); i++){
            if(donations.get(i).getCourier().getId() == courier.getId()){
                model.addAttribute("input"," kuriera " + courier.getCourierName() + " ponieważ ma niezrealizowane zlecenia!");
                return "err/cannotdelete";
            }
        }

        courierRepository.delete(courier);
        return "redirect:/";
    }

    @RequestMapping(value = "/addCourier")
    public String addCourier(Model model) {
        model.addAttribute("courier", new Courier());
        return "admin/addCourier";
    }

    @RequestMapping(value = "/doAddCourier", method = RequestMethod.POST)
    public String processAddClient(@Valid Courier courier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/addCourier";
        }
        courierRepository.save(courier);
        return "redirect:/admin/couriers";
    }

    @RequestMapping(value = "/editCourier/{id}")
    public String editCourierPage(@PathVariable Long id, Model model) {
        Optional<Courier> from = courierRepository.findById(id);
        Courier c = from.orElse(null);
        if (c == null) {
            model.addAttribute("input","takiego kuriera");
            return "err/notfound";
        }
        model.addAttribute("courier", c);

        return "admin/editCourier";


    }

    @RequestMapping(value = "/doEditCourier", method = RequestMethod.POST)
    public String doEdit(@Valid Courier courier, BindingResult bindingResult) {
        Optional<Courier> from = courierRepository.findById(courier.getId());
        Courier c = from.orElse(null);
        if (bindingResult.hasErrors() || c == null) {
            return "admin/editCourier";
        }

        c.setCourierName(courier.getCourierName());
        c.setEmail(courier.getEmail());
        c.setWww(courier.getWww());
        c.setTel(courier.getTel());
        c.setColour(courier.getColour());
        courierRepository.save(c);
        return "redirect:/admin/couriers";
    }

}
