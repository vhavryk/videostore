package com.test.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MovieType.
 */
@Entity
@Table(name = "movie_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MovieType implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  private Integer bonus;

  @OneToMany(mappedBy = "movieType")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  private Set<MovieTypePrice> movieTypePrices = new HashSet<>();

  @OneToMany(mappedBy = "movieType")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  private Set<Movie> movies = new HashSet<>();

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

  public MovieType name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<MovieTypePrice> getMovieTypePrices() {
    return movieTypePrices;
  }

  public MovieType movieTypePrices(Set<MovieTypePrice> movieTypePrices) {
    this.movieTypePrices = movieTypePrices;
    return this;
  }

  public MovieType addMovieTypePrice(MovieTypePrice movieTypePrice) {
    this.movieTypePrices.add(movieTypePrice);
    movieTypePrice.setMovieType(this);
    return this;
  }

  public MovieType removeMovieTypePrice(MovieTypePrice movieTypePrice) {
    this.movieTypePrices.remove(movieTypePrice);
    movieTypePrice.setMovieType(null);
    return this;
  }

  public void setMovieTypePrices(Set<MovieTypePrice> movieTypePrices) {
    this.movieTypePrices = movieTypePrices;
  }

  public Set<Movie> getMovies() {
    return movies;
  }

  public MovieType movies(Set<Movie> movies) {
    this.movies = movies;
    return this;
  }

  public MovieType addMovie(Movie movie) {
    this.movies.add(movie);
    movie.setMovieType(this);
    return this;
  }

  public MovieType removeMovie(Movie movie) {
    this.movies.remove(movie);
    movie.setMovieType(null);
    return this;
  }

  public void setMovies(Set<Movie> movies) {
    this.movies = movies;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


  public Integer getBonus() {
    return bonus;
  }

  public void setBonus(Integer bonus) {
    this.bonus = bonus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MovieType)) {
      return false;
    }
    return id != null && id.equals(((MovieType) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString() {
    return "MovieType{" +
        "id=" + getId() +
        ", name='" + getName() + "'" +
        "}";
  }
}
