package com.test.domain;

import java.time.LocalDate;
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

@Entity
@Table(name = "order1")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date")
  private LocalDate localDate;

  @OneToMany(mappedBy = "order")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  private Set<Rent> rents = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public Set<Rent> getRents() {
    return rents;
  }

  public Order rents(Set<Rent> rents) {
    this.rents = rents;
    return this;
  }

  public Order addRent(Rent rent) {
    this.rents.add(rent);
    rent.setOrder(this);
    return this;
  }

  public Order removeRent(Rent rent) {
    this.rents.remove(rent);
    rent.setOrder(null);
    return this;
  }

  public void setRents(Set<Rent> rents) {
    this.rents = rents;
  }
}
