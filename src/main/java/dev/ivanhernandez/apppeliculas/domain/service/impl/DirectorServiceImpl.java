package dev.ivanhernandez.apppeliculas.domain.service.impl;

import dev.ivanhernandez.apppeliculas.domain.entity.Director;
import dev.ivanhernandez.apppeliculas.domain.service.DirectorService;
import dev.ivanhernandez.apppeliculas.exception.ResourceNotFoundException;
import dev.ivanhernandez.apppeliculas.persistence.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public int insert(Director director) {
        return directorRepository.insert(director);
    }

    @Override
    public Director find (int id) {
        Director director = directorRepository.find(id);
        if (director == null) {
            throw new ResourceNotFoundException("Director not found with id: " + id);
        }
        return director;
    }

    @Override
    public void update(Director director) {
        find(director.getId());
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        find(id);
        directorRepository.delete(id);
    }
}
