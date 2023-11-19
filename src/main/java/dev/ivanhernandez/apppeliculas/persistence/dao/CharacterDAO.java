package dev.ivanhernandez.apppeliculas.persistence.dao;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.exception.SQLStatementException;
import dev.ivanhernandez.apppeliculas.mapper.CharacterMapper;
import dev.ivanhernandez.apppeliculas.persistence.model.CharacterEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CharacterDAO {

    public List<CharacterEntity> findByMovieId(Connection connection, int movieId) {
        final String SQL = "SELECT * FROM actors_movies WHERE movie_id = ?";
        List<Object> params = List.of(movieId);
        List<CharacterEntity> characterEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, params);
            while (resultSet.next()) {
                characterEntities.add(CharacterMapper.mapper.toCharacterEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
        return characterEntities;
    }

    public int create(Connection connection, String character, int movieId, int actorId) {
        final String SQL = "INSERT INTO actors_movies (movie_id, actor_id, characters) VALUES (?, ?, ?)";
        List<Object> params = List.of(movieId, actorId, character);
        return DBUtil.insert(connection, SQL, params);
    }

    public void update(Connection connection, CharacterEntity characterEntity, int actorId) {
        final String SQL = "UPDATE actors_movies SET characters = ?, actor_id = ? WHERE id = ?";
        List<Object> params = List.of(characterEntity.getName(), actorId, characterEntity.getId());
        DBUtil.update(connection, SQL, params);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM actors_movies WHERE id = ?";
        List<Object> params = List.of(id);
        DBUtil.delete(connection, SQL, params);
    }

    public Optional<CharacterEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM actors_movies WHERE id = ? LIMIT 1";
        List<Object> params = List.of(id);
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, params);
            return Optional.ofNullable(resultSet.next() ? CharacterMapper.mapper.toCharacterEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
    }
}
