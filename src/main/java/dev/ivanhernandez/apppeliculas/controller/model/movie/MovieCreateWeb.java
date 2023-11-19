package dev.ivanhernandez.apppeliculas.controller.model.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieCreateWeb {

        private String title;
        private int year;
        private int runtime;
        private int directorId;
        private HashMap<String, Integer> characters;
}
