package dev.ivanhernandez.apppeliculas.persistence.impl;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.exception.SQLStatmentException;
import dev.ivanhernandez.apppeliculas.persistence.ActorRepository;
import dev.ivanhernandez.apppeliculas.domain.entity.Actor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Override
    public int insert(Actor actor) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        try (Connection connection = DBUtil.open()){
            int id = DBUtil.insert(connection, SQL, List.of(actor.getName(), actor.getBirthYear(), actor.getDeathYear()));
            DBUtil.close(connection);
            return id;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public List<Actor> getAll() {
        final String SQL = "SELECT * FROM actors";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            DBUtil.close(connection);
            List<Actor> actors = new ArrayList<>();
            while (resultSet.next()) {
                actors.add(
                        new Actor(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                resultSet.getInt("deathYear")
                        )
                );
            }
            return actors;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public Actor find(int id) {
        final String SQL = "SELECT * FROM actors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if (resultSet.next()) {
                return new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        resultSet.getInt("deathYear")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public void update(Actor actor){
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        try (Connection connection = DBUtil.open()){
            DBUtil.update(connection, SQL, List.of(actor.getName(), actor.getBirthYear(), actor.getDeathYear(), actor.getId()));
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public void delete(int id){
        final String SQL = "DELETE FROM actors WHERE id = ?";
        try (Connection connection = DBUtil.open()){
            DBUtil.delete(connection, SQL, List.of(id));
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }
}
