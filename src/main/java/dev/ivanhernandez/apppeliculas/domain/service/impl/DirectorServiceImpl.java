package dev.ivanhernandez.apppeliculas.domain.service.impl;

import dev.ivanhernandez.apppeliculas.domain.entity.Director;
import dev.ivanhernandez.apppeliculas.domain.service.DirectorService;
import dev.ivanhernandez.apppeliculas.exception.ResourceNotFoundException;
import dev.ivanhernandez.apppeliculas.domain.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    public int create(Director director){
        return directorRepository.insert(director);
    }

    @Override
    public void update(Director director) {
        this.find(director.getId());
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        this.find(id);
        directorRepository.delete(id);
    }

    @Override
    public Director find(int id) {
        return directorRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + id));
    }
}
