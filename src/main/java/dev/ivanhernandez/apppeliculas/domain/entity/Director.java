package dev.ivanhernandez.apppeliculas.domain.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Director {
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;

    public Director(String name, int birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
}
