package com.test.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.test.domain.Customer} entity.
 */
public class CustomerDTO implements Serializable {
    
    private Long id;

    private String name;

    private Long bonus;
    
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

    public Long getBonus() {
        return bonus;
    }

    public void setBonus(Long customerBonus) {
        this.bonus = customerBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerDTO)) {
            return false;
        }

        return id != null && id.equals(((CustomerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", bonusId=" + getBonus() +
            "}";
    }
}
