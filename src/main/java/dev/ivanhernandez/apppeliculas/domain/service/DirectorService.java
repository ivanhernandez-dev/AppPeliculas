package dev.ivanhernandez.apppeliculas.domain.service;

import dev.ivanhernandez.apppeliculas.domain.entity.Director;

public interface DirectorService {
    int insert(Director director);

    Director find(int id);

    void update(Director director);

    void delete(int id);
}
