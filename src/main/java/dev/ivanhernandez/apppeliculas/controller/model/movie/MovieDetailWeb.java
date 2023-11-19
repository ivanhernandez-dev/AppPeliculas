package dev.ivanhernandez.apppeliculas.controller.model.movie;

import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorListWeb;
import dev.ivanhernandez.apppeliculas.controller.model.character.CharacterListWeb;
import dev.ivanhernandez.apppeliculas.controller.model.director.DirectorListWeb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieDetailWeb {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorListWeb director;
    private List<CharacterListWeb> characters;
}
