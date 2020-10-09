package pl.bw.charity.domain.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipCode;
    private String city;
    private String address;
    private String tel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate pickUpDate;

    @DateTimeFormat(pattern = "HH:mm")
    LocalTime pickUpTime;

    String comments ;

    @ManyToOne
    private Organization organization;

    @OneToMany
    @JoinColumn(name = "donation_id")
    Set<DonationDetails> donationDetails = new HashSet<>();

    public Donation() {
    }

    public Donation(String zipCode, String city, String address, String tel, LocalDate pickUpDate, LocalTime pickUpTime, String comments, Organization organization) {
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.tel = tel;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.comments = comments;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<DonationDetails> getDonationDetails() {
        return donationDetails;
    }

    public void setDonationDetails(Set<DonationDetails> donationDetails) {
        this.donationDetails = donationDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Donation donation = (Donation) o;

        return id != null ? id.equals(donation.id) : donation.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
