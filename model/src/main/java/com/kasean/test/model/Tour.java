package com.kasean.test.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull
    private Long id;

    @Column(name = "direction", nullable = false)
    private String direction;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "coast", nullable = false)
    private Integer coast;

    @Column(name = "user_id")
    private Long user_id;

    public Tour() {
    }


    public Tour(String direction, LocalDate date, Integer coast) {
        this.direction = direction;
        this.date = date;
        this.coast = coast;
    }

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
