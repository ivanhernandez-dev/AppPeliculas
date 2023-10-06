package dev.ivanhernandez.apppeliculas.domain.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;

    public Actor(String name, int birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
}
