package dev.ivanhernandez.apppeliculas.domain.repository;

import dev.ivanhernandez.apppeliculas.domain.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {

    int insert(Actor actor);

    void update(Actor actor);

    Optional<Actor> find(int id);

    void delete(int id);
}
