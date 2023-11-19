package dev.ivanhernandez.apppeliculas.domain.service.impl;

import dev.ivanhernandez.apppeliculas.domain.entity.Actor;
import dev.ivanhernandez.apppeliculas.domain.service.ActorService;
import dev.ivanhernandez.apppeliculas.exception.ResourceNotFoundException;
import dev.ivanhernandez.apppeliculas.domain.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public int create(Actor actor){
        return actorRepository.insert(actor);
    }

    @Override
    public void update(Actor actor) {
        this.find(actor.getId());
        actorRepository.update(actor);
    }

    @Override
    public void delete(int id) {
        this.find(id);
        actorRepository.delete(id);
    }

    @Override
    public Actor find(int id) {
        return actorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));
    }
}
