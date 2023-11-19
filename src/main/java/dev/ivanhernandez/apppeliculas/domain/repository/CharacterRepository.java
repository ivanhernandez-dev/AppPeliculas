package dev.ivanhernandez.apppeliculas.domain.repository;

import dev.ivanhernandez.apppeliculas.domain.entity.Character;

import java.util.Optional;

public interface CharacterRepository {
    void update(Character character, int actorId);

    Optional<Character> find(int id);
}
