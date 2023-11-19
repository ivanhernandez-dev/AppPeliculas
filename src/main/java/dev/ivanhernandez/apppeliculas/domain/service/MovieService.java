package dev.ivanhernandez.apppeliculas.domain.service;

import dev.ivanhernandez.apppeliculas.domain.entity.Movie;

import java.util.List;
import java.util.Map;
import dev.ivanhernandez.apppeliculas.domain.entity.Character;

public interface MovieService {
    List<Movie> getAll(Integer page, Integer pageSize);

    List<Movie> getAll();
    int getTotalNumberOfRecords();
    Movie find(int id);

    int create(Movie movie, int directorId, Map<String, Integer> characters);

    void update(Movie movie, int directorId);

    void delete(int id);

    void deleteCharacter(int movieId, int characterId);

    void addCharacter(Character character, int actorId, int movieId);
}
