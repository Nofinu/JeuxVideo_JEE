package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "jeux")
@Getter
@Setter
public class Jeux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float score;
    private LocalDate releaseDate;

    private String review;

    private String type;


    public Jeux() {
    }

    public Jeux(String name, float score, LocalDate releaseDate, String review, String type) {
        this.name = name;
        this.score = score;
        this.releaseDate = releaseDate;
        this.review = review;
        this.type = type;
    }
}
