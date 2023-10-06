package dev.ivanhernandez.apppeliculas.domain.service;

import dev.ivanhernandez.apppeliculas.domain.entity.Actor;

public interface ActorService {
    int insert(Actor Actor);

    Actor find(int id);

    void update(Actor Actor);

    void delete(int id);
}
