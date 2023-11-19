package dev.ivanhernandez.apppeliculas.domain.service.impl;

import dev.ivanhernandez.apppeliculas.domain.entity.Character;
import dev.ivanhernandez.apppeliculas.domain.repository.CharacterRepository;
import dev.ivanhernandez.apppeliculas.domain.service.CharacterService;
import dev.ivanhernandez.apppeliculas.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Override
    public void update(Character character, int actorId) {
        this.find(character.getId());
        characterRepository.update(character, actorId);
    }

    @Override
    public Character find(int id) {
        return characterRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character not found with id: " + id));
    }
}
