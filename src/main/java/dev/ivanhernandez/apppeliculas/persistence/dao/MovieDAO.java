package dev.ivanhernandez.apppeliculas.persistence.dao;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.exception.SQLStatementException;
import dev.ivanhernandez.apppeliculas.mapper.MovieMapper;
import dev.ivanhernandez.apppeliculas.persistence.model.MovieEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieDAO {

    public List<MovieEntity> findAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String sql = "SELECT * FROM movies";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<MovieEntity> movieEntities = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                movieEntities.add(MovieMapper.mapper.toMovieEntity(resultSet));
            }
            return movieEntities;
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + sql);
        }
    }

    public Optional<MovieEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next() ? MovieMapper.mapper.toMovieEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
    }

    public int getTotalNumberOfRecords(Connection connection) {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new SQLStatementException("SQL: " + SQL);
        }
    }

    public int create(Connection connection, MovieEntity movieEntity, int directorId) {
        final String SQL = "INSERT INTO movies (title, year, runtime, director_id) VALUES (?, ?, ?, ?)";
        return DBUtil.insert(connection, SQL, List.of(movieEntity.getTitle(), movieEntity.getYear(), movieEntity.getRuntime(), directorId));
    }

    public void update(Connection connection, MovieEntity movieEntity, int directorId) {
        final String SQL = "UPDATE movies SET title = ?, year = ?, runtime = ?, director_id = ? WHERE id = ?";
        DBUtil.update(connection, SQL, List.of(movieEntity.getTitle(), movieEntity.getYear(), movieEntity.getRuntime(), directorId, movieEntity.getId()));
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM movies WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
    }
}
