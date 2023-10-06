package dev.ivanhernandez.apppeliculas.domain.service.impl;

import dev.ivanhernandez.apppeliculas.domain.entity.Actor;
import dev.ivanhernandez.apppeliculas.domain.service.ActorService;
import dev.ivanhernandez.apppeliculas.exception.ResourceNotFoundException;
import dev.ivanhernandez.apppeliculas.persistence.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;

    @Override
    public int insert(Actor Actor) {
        return actorRepository.insert(Actor);
    }

    @Override
    public Actor find (int id) {
        Actor Actor = actorRepository.find(id);
        if (Actor == null) {
            throw new ResourceNotFoundException("Actor not found with id: " + id);
        }
        return Actor;
    }

    @Override
    public void update(Actor Actor) {
        find(Actor.getId());
        actorRepository.update(Actor);
    }

    @Override
    public void delete(int id) {
        find(id);
        actorRepository.delete(id);
    }
}
