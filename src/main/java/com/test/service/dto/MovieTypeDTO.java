package com.test.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.test.domain.MovieType} entity.
 */
public class MovieTypeDTO implements Serializable {
    
    private Long id;

    private String name;

    private Integer bonus;

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((MovieTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
