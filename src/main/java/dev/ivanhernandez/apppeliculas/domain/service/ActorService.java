package dev.ivanhernandez.apppeliculas.domain.service;

import dev.ivanhernandez.apppeliculas.domain.entity.Actor;

public interface ActorService {
    int create(Actor actor);

    void update(Actor actor);

    void delete(int id);

    public Actor find(int id);
}
