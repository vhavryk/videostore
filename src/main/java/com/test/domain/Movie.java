package com.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Movie.
 */
@Entity
@Table(name = "movie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "movie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Rent> rents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private MovieType movieType;

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

    public Movie name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Rent> getRents() {
        return rents;
    }

    public Movie rents(Set<Rent> rents) {
        this.rents = rents;
        return this;
    }

    public Movie addRent(Rent rent) {
        this.rents.add(rent);
        rent.setMovie(this);
        return this;
    }

    public Movie removeRent(Rent rent) {
        this.rents.remove(rent);
        rent.setMovie(null);
        return this;
    }

    public void setRents(Set<Rent> rents) {
        this.rents = rents;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public Movie movieType(MovieType movieType) {
        this.movieType = movieType;
        return this;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie)) {
            return false;
        }
        return id != null && id.equals(((Movie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Movie{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
