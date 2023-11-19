package dev.ivanhernandez.apppeliculas.persistence.dao;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.exception.SQLStatementException;
import dev.ivanhernandez.apppeliculas.mapper.ActorMapper;
import dev.ivanhernandez.apppeliculas.mapper.MovieMapper;
import dev.ivanhernandez.apppeliculas.persistence.model.ActorEntity;
import dev.ivanhernandez.apppeliculas.persistence.model.MovieEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ActorDAO {

    public Optional<ActorEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next() ? ActorMapper.mapper.toActorEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM actors WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
    }

    public int insert(Connection connection, ActorEntity actorEntity) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());

        return DBUtil.insert(connection, SQL, params);
    }

    public void update(Connection connection, ActorEntity actorEntity) {
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        params.add(actorEntity.getId());
        DBUtil.update(connection, SQL, params);
    }

    public ActorEntity findByCharacter(Connection connection, int characterId) {
        final String SQL = "SELECT a.* FROM actors a INNER JOIN actors_movies am ON a.id = am.actor_id WHERE am.id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(characterId));
            return resultSet.next() ? ActorMapper.mapper.toActorEntity(resultSet) : null;
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
    }
}
