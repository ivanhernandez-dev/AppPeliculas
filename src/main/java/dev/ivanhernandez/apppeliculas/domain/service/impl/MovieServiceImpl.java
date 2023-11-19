package dev.ivanhernandez.apppeliculas.domain.service.impl;

import dev.ivanhernandez.apppeliculas.domain.entity.Movie;
import dev.ivanhernandez.apppeliculas.domain.entity.Character;
import dev.ivanhernandez.apppeliculas.domain.service.ActorService;
import dev.ivanhernandez.apppeliculas.domain.service.CharacterService;
import dev.ivanhernandez.apppeliculas.domain.service.DirectorService;
import dev.ivanhernandez.apppeliculas.domain.service.MovieService;
import dev.ivanhernandez.apppeliculas.exception.ResourceNotFoundException;
import dev.ivanhernandez.apppeliculas.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private CharacterService characterService;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        return movieRepository.getAll(page, pageSize);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll(null, null);
    }

    @Override
    public Movie find(int id) {
        Movie movie = movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        return movie;
    }

    public int getTotalNumberOfRecords(){
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public int create(Movie movie, int directorId, Map<String, Integer> characters) {
        directorService.find(directorId);
        characters.forEach((character, actorId) -> {
            actorService.find(actorId);
        });
        return movieRepository.create(movie, directorId, characters);
    }

    @Override
    public void update(Movie movie, int directorId){
        this.find(movie.getId());
        movieRepository.update(movie, directorId);
    }

    @Override
    public void delete(int id) {
        this.find(id);
        movieRepository.delete(id);
    }

    @Override
    public void deleteCharacter(int movieId, int characterId) {
        this.find(movieId);
        characterService.find(characterId);
        movieRepository.deleteCharacter(characterId);
    }

    @Override
    public void addCharacter(Character character, int actorId, int movieId) {
        this.find(movieId);
        actorService.find(actorId);
        movieRepository.addCharacter(character, actorId, movieId);
    }
}
