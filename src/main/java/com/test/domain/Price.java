package com.test.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Price.
 */
@Entity
@Table(name = "price")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "price")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MovieTypePrice> moviewTypePrices = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Price name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public Price price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<MovieTypePrice> getMoviewTypePrices() {
        return moviewTypePrices;
    }

    public Price moviewTypePrices(Set<MovieTypePrice> movieTypePrices) {
        this.moviewTypePrices = movieTypePrices;
        return this;
    }

    public Price addMoviewTypePrice(MovieTypePrice movieTypePrice) {
        this.moviewTypePrices.add(movieTypePrice);
        movieTypePrice.setPrice(this);
        return this;
    }

    public Price removeMoviewTypePrice(MovieTypePrice movieTypePrice) {
        this.moviewTypePrices.remove(movieTypePrice);
        movieTypePrice.setPrice(null);
        return this;
    }

    public void setMoviewTypePrices(Set<MovieTypePrice> movieTypePrices) {
        this.moviewTypePrices = movieTypePrices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Price)) {
            return false;
        }
        return id != null && id.equals(((Price) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Price{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            "}";
    }
}
