package dev.ivanhernandez.apppeliculas.persistence.repository.impl;

import dev.ivanhernandez.apppeliculas.domain.entity.Movie;
import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.domain.repository.MovieRepository;
import dev.ivanhernandez.apppeliculas.mapper.CharacterMapper;
import dev.ivanhernandez.apppeliculas.mapper.MovieMapper;
import dev.ivanhernandez.apppeliculas.persistence.dao.ActorDAO;
import dev.ivanhernandez.apppeliculas.persistence.dao.CharacterDAO;
import dev.ivanhernandez.apppeliculas.persistence.dao.DirectorDAO;
import dev.ivanhernandez.apppeliculas.persistence.dao.MovieDAO;
import dev.ivanhernandez.apppeliculas.persistence.model.CharacterEntity;
import dev.ivanhernandez.apppeliculas.persistence.model.MovieEntity;
import dev.ivanhernandez.apppeliculas.domain.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;
    @Autowired
    DirectorDAO directorDao;
    @Autowired
    CharacterDAO characterDao;
    @Autowired
    ActorDAO actorDao;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<MovieEntity> movieEntities = movieDAO.findAll(connection, page, pageSize);
            return (List<Movie>) movieEntities.stream()
                    .map(MovieMapper.mapper::toMovie)
                    .toList();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            MovieEntity movieEntity = movieDAO.find(connection, id).orElse(null);
            if (movieEntity != null) {
                movieEntity.getDirectorEntity(connection, directorDao);
                List<CharacterEntity> characterEntities = movieEntity.getCharacterEntities(connection, characterDao);
                characterEntities.forEach(characterEntity -> {
                    characterEntity.getActorEntity(connection, actorDao);
                });
            }
            return Optional.ofNullable(movieEntity != null ? MovieMapper.mapper.toMovie(movieEntity) : null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try(Connection connection = DBUtil.open(true)) {
            return movieDAO.getTotalNumberOfRecords(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(Movie movie, int directorId, Map<String, Integer> characters) {
        try(Connection connection = DBUtil.open(false)) {
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            int movieId = movieDAO.create(connection, movieEntity, directorId);
            movieEntity.setId(movieId);
            characters.forEach((character, actorId) -> {
                characterDao.create(connection, character, movieId, actorId);
            });
            connection.commit();
            return movieId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Movie movie, int directorId){
        try(Connection connection = DBUtil.open(true)) {
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            movieDAO.update(connection, movieEntity, directorId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DBUtil.open(true)) {
            movieDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCharacter(int characterId) {
        try(Connection connection = DBUtil.open(true)) {
            characterDao.delete(connection, characterId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCharacter(Character character, int actorId, int movieId) {
        try(Connection connection = DBUtil.open(true)) {
            CharacterEntity characterEntity = CharacterMapper.mapper.toCharacterEntity(character);
            characterDao.create(connection, characterEntity.getName(), movieId, actorId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}