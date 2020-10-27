package com.test.service.dto;

import com.test.domain.Rent;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link Rent} entity.
 */
public class RentDTO implements Serializable {
    
    private Long id;

    private LocalDate date;

    private Boolean paid;

    private Integer days;

    private LocalDate returnedDate;

    private Boolean returnedPaid;

    private Boolean returned;

    private Long customerId;

    private Long movieId;

    private Long orderId;
    private Double price;
    private Double returnPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Boolean getReturnedPaid() {
        return returnedPaid;
    }

    public void setReturnedPaid(Boolean returnedPaid) {
        this.returnedPaid = returnedPaid;
    }

    public Boolean isReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RentDTO)) {
            return false;
        }

        return id != null && id.equals(((RentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RentDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", paid=" + getPaid() +
            ", days=" + getDays() +
            ", returnedDate='" + getReturnedDate() + "'" +
            ", returnedPaid=" + getReturnedPaid() +
            ", returned='" + isReturned() + "'" +
            ", customerId=" + getCustomerId() +
            ", movieId=" + getMovieId() +
            "}";
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setReturnPrice(Double returnPrice) {
        this.returnPrice = returnPrice;
    }

    public Double getReturnPrice() {
        return returnPrice;
    }
}
