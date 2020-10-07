package pl.bw.charity.domain.model;

import javax.persistence.*;

@Entity
public class DonationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Donation donation;

    @ManyToOne
    private Good good;

    private int quantity;

    public DonationDetails() {
    }

    public DonationDetails(Donation donation, Good good, int quantity) {
        this.donation = donation;
        this.good = good;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
