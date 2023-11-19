package dev.ivanhernandez.apppeliculas.controller.model.movie;

import dev.ivanhernandez.apppeliculas.controller.model.director.DirectorListWeb;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieUpdateWeb {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private int directorId;
}
