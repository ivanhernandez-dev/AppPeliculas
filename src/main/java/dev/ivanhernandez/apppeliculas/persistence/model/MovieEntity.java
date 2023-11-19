package dev.ivanhernandez.apppeliculas.persistence.model;

import dev.ivanhernandez.apppeliculas.persistence.dao.CharacterDAO;
import dev.ivanhernandez.apppeliculas.persistence.dao.DirectorDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private DirectorEntity directorEntity;
    private List<CharacterEntity> characterEntities;

    public MovieEntity(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

    public DirectorEntity getDirectorEntity(Connection connection, DirectorDAO directorDAO) {
        if (directorEntity == null) {
            directorEntity = directorDAO.findByMovie(connection, id);
        }
        return directorEntity;
    }

    public List<CharacterEntity> getCharacterEntities(Connection connection, CharacterDAO characterDAO) {
        if (characterEntities == null) {
            characterEntities = characterDAO.findByMovieId(connection, id);
        }
        return characterEntities;
    }
}