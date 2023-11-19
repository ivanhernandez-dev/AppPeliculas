package dev.ivanhernandez.apppeliculas.domain.service;

import dev.ivanhernandez.apppeliculas.domain.entity.Character;

public interface CharacterService {
    void update(Character character, int actorId);

    Character find(int id);
}
