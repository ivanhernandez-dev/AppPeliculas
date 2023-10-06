package dev.ivanhernandez.apppeliculas.persistence;

import dev.ivanhernandez.apppeliculas.domain.entity.Director;

import java.util.List;

public interface DirectorRepository {
    int insert(Director director);

    List<Director> getAll();

    Director find(int id);

    void update(Director director);

    void delete(int id);
}
