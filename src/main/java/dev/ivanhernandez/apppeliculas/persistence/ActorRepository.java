package dev.ivanhernandez.apppeliculas.persistence;

import dev.ivanhernandez.apppeliculas.domain.entity.Actor;

import java.util.List;

public interface ActorRepository {

    int insert(Actor actor);

    List<Actor> getAll();

    Actor find(int id);

    void update(Actor actor);

    void delete(int id);
}
