package dev.ivanhernandez.apppeliculas.domain.service;

import dev.ivanhernandez.apppeliculas.domain.entity.Director;

public interface DirectorService {
    int create(Director director);

    void update(Director director);

    void delete(int id);

    Director find(int id);
}
