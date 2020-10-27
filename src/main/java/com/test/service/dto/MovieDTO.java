package com.test.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.test.domain.Movie} entity.
 */
public class MovieDTO implements Serializable {
    
    private Long id;

    private String name;

    private Long movieTypeId;

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

    public Long getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(Long movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieDTO)) {
            return false;
        }

        return id != null && id.equals(((MovieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", movieTypeId=" + getMovieTypeId() +
            "}";
    }
}
