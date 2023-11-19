package dev.ivanhernandez.apppeliculas.domain.repository;

import dev.ivanhernandez.apppeliculas.domain.entity.Character;
import dev.ivanhernandez.apppeliculas.domain.entity.Movie;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieRepository {

    List<Movie> getAll(Integer page, Integer pageSize);

    Optional<Movie> find(int id);

    int getTotalNumberOfRecords();

    int create(Movie movie, int directorId, Map<String, Integer> characters);

    void update(Movie movie, int directorId);

    void delete(int id);

    void deleteCharacter(int characterId);

    void addCharacter(Character character, int actorId, int movieId);
}
