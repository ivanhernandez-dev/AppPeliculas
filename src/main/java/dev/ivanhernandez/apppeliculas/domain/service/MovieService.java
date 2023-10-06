package dev.ivanhernandez.apppeliculas.domain.service;

import dev.ivanhernandez.apppeliculas.domain.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAll(Optional<Integer> page, int pageSize);

    Movie find(int id);

    int getTotalNumberOfRecords();
}
