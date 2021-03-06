package pl.bw.charity.domain.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinColumn(name = "good_id")
    @OneToMany(fetch = FetchType.EAGER)
    Set<DonationDetails> donationDetails = new HashSet<>();

    public Good() {
    }

    public Good(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Good good = (Good) o;

        return id != null ? id.equals(good.id) : good.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
