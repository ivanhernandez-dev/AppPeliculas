package dev.ivanhernandez.apppeliculas.persistence.impl;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.domain.entity.Director;
import dev.ivanhernandez.apppeliculas.exception.SQLStatmentException;
import dev.ivanhernandez.apppeliculas.persistence.DirectorRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Override
    public int insert(Director director) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        try (Connection connection = DBUtil.open()){
            int id = DBUtil.insert(connection, SQL, List.of(director.getName(), director.getBirthYear(), director.getDeathYear()));
            DBUtil.close(connection);
            return id;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public List<Director> getAll() {
        final String SQL = "SELECT * FROM directors";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            DBUtil.close(connection);
            List<Director> directors = new ArrayList<>();
            while (resultSet.next()) {
                directors.add(
                        new Director(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                resultSet.getInt("deathYear")
                        )
                );
            }
            return directors;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public Director find(int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return new Director(
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
    public void update(Director director) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        try (Connection connection = DBUtil.open()){
            DBUtil.update(connection, SQL, List.of(director.getName(), director.getBirthYear(), director.getDeathYear(), director.getId()));
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public void delete(int id){
        final String SQL = "DELETE FROM directors WHERE id = ?";
        try (Connection connection = DBUtil.open()){
            DBUtil.update(connection, SQL, List.of(id));
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }
}
