package dev.ivanhernandez.apppeliculas.persistence.dao;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.domain.entity.Director;
import dev.ivanhernandez.apppeliculas.domain.repository.DirectorRepository;
import dev.ivanhernandez.apppeliculas.exception.SQLStatementException;
import dev.ivanhernandez.apppeliculas.mapper.DirectorMapper;
import dev.ivanhernandez.apppeliculas.persistence.model.DirectorEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DirectorDAO {


    public Optional<DirectorEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next() ? DirectorMapper.mapper.toDirectorEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
    }

    public int insert(Connection connection, DirectorEntity directorEntity) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());

        return DBUtil.insert(connection, SQL, params);
    }

    public void update(Connection connection, DirectorEntity directorEntity) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());
        params.add(directorEntity.getId());
        DBUtil.update(connection, SQL, params);
    }

    public DirectorEntity findByMovie(Connection connection, int movieId) {
        final String SQL = "SELECT * FROM directors JOIN movies ON directors.id = movies.director_id WHERE movies.id = ?";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            return resultSet.next() ? DirectorMapper.mapper.toDirectorEntity(resultSet) : null;
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
    }
}
