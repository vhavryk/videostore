package com.test.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.test.domain.MovieTypePrice} entity.
 */
public class MovieTypePriceDTO implements Serializable {
    
    private Long id;

    private Integer days;

    private Integer nextDays;


    private Long movieTypeId;

    private Long priceId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getNextDays() {
        return nextDays;
    }

    public void setNextDays(Integer nextDays) {
        this.nextDays = nextDays;
    }

    public Long getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(Long movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieTypePriceDTO)) {
            return false;
        }

        return id != null && id.equals(((MovieTypePriceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieTypePriceDTO{" +
            "id=" + getId() +
            ", days=" + getDays() +
            ", nextDays=" + getNextDays() +
            ", movieTypeId=" + getMovieTypeId() +
            ", priceId=" + getPriceId() +
            "}";
    }
}
