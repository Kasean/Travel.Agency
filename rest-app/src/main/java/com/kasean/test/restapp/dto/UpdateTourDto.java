package com.kasean.test.restapp.dto;

import java.time.LocalDate;

public class UpdateTourDto {

    private String direction;
    private LocalDate date;
    private Integer coast;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getCoast() {
        return coast;
    }

    public void setCoast(Integer coast) {
        this.coast = coast;
    }
}
