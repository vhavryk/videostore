package com.test.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.test.domain.BonusPrice} entity.
 */
public class BonusPriceDTO implements Serializable {
    
    private Long id;

    private Double price;

    private Boolean active;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BonusPriceDTO)) {
            return false;
        }

        return id != null && id.equals(((BonusPriceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BonusPriceDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", active='" + isActive() + "'" +
            "}";
    }
}
