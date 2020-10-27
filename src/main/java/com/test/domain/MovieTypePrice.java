package com.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MovieTypePrice.
 */
@Entity
@Table(name = "movie_type_price")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MovieTypePrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "days")
    private Integer days;

    @Column(name = "next_days")
    private Integer nextDays;

    @ManyToOne
    @JsonIgnoreProperties(value = "movieTypePrices", allowSetters = true)
    private MovieType movieType;

    @ManyToOne
    @JsonIgnoreProperties(value = "moviewTypePrices", allowSetters = true)
    private Price price;

    private Integer bonus;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public MovieTypePrice days(Integer days) {
        this.days = days;
        return this;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getNextDays() {
        return nextDays;
    }

    public MovieTypePrice nextDays(Integer nextDays) {
        this.nextDays = nextDays;
        return this;
    }

    public void setNextDays(Integer nextDays) {
        this.nextDays = nextDays;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public MovieTypePrice movieType(MovieType movieType) {
        this.movieType = movieType;
        return this;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Price getPrice() {
        return price;
    }

    public MovieTypePrice price(Price price) {
        this.price = price;
        return this;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieTypePrice)) {
            return false;
        }
        return id != null && id.equals(((MovieTypePrice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieTypePrice{" +
            "id=" + getId() +
            ", days=" + getDays() +
            ", nextDays=" + getNextDays() +
            "}";
    }
}
