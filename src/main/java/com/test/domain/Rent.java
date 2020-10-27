package com.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Rent.
 */
@Entity
@Table(name = "rent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rent implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "paid")
  private Boolean paid;

  @Column(name = "days")
  private Integer days;

  @Column(name = "returned_date")
  private LocalDate returnedDate;

  @Column(name = "returned_paid")
  private Boolean returnedPaid;

  private Double returnPrice;

  @ManyToOne
  @JsonIgnoreProperties(value = "rents", allowSetters = true)
  private Customer customer;

  @ManyToOne
  @JsonIgnoreProperties(value = "rents", allowSetters = true)
  private Movie movie;

  @ManyToOne
  @JsonIgnoreProperties(value = "rents", allowSetters = true)
  private Order order;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Double getReturnPrice() {
    return returnPrice;
  }

  public void setReturnPrice(Double returnPrice) {
    this.returnPrice = returnPrice;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public Rent date(LocalDate date) {
    this.date = date;
    return this;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Boolean getPaid() {
    return paid;
  }

  public Rent paid(Boolean paid) {
    this.paid = paid;
    return this;
  }

  public void setPaid(Boolean paid) {
    this.paid = paid;
  }

  public Integer getDays() {
    return days;
  }

  public Rent days(Integer days) {
    this.days = days;
    return this;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public LocalDate getReturnedDate() {
    return returnedDate;
  }

  public Rent returnedDate(LocalDate returnedDate) {
    this.returnedDate = returnedDate;
    return this;
  }

  public void setReturnedDate(LocalDate returnedDate) {
    this.returnedDate = returnedDate;
  }

  public Boolean getReturnedPaid() {
    return returnedPaid;
  }

  public Rent returnedPaid(Boolean returnedPaid) {
    this.returnedPaid = returnedPaid;
    return this;
  }

  public void setReturnedPaid(Boolean returnedPaid) {
    this.returnedPaid = returnedPaid;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Rent customer(Customer customer) {
    this.customer = customer;
    return this;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Movie getMovie() {
    return movie;
  }

  public Rent movie(Movie movie) {
    this.movie = movie;
    return this;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Rent)) {
      return false;
    }
    return id != null && id.equals(((Rent) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  // prettier-ignore
  @Override
  public String toString() {
    return "Rent{" +
        "id=" + getId() +
        ", date='" + getDate() + "'" +
        ", paid=" + getPaid() +
        ", days=" + getDays() +
        ", returnedDate='" + getReturnedDate() + "'" +
        ", returnedPaid=" + getReturnedPaid() +
        "}";
  }
}
