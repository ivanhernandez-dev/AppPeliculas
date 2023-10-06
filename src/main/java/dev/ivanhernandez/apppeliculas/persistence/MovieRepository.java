package dev.ivanhernandez.apppeliculas.persistence;

import dev.ivanhernandez.apppeliculas.domain.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    List<Movie> getAll(Optional<Integer> page, int pageSize);

    Movie find(int id);

    int getTotalNumberOfRecords();
}
