package dev.ivanhernandez.apppeliculas.persistence.impl;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.domain.entity.Movie;
import dev.ivanhernandez.apppeliculas.exception.DBConnectionException;
import dev.ivanhernandez.apppeliculas.exception.ResourceNotFoundException;
import dev.ivanhernandez.apppeliculas.exception.SQLStatmentException;
import dev.ivanhernandez.apppeliculas.persistence.MovieRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {


    @Override
    public List<Movie> getAll(Optional<Integer> page, int pageSize) {
        String sql = "SELECT * FROM movies";
        if(page.isPresent()) {
            int offset = (page.get()-1) * pageSize;
            sql += String.format(" LIMIT %d, %d", offset, pageSize);
        }
        List<Movie> movies = new ArrayList<>();
            try (Connection connection = DBUtil.open()){
                ResultSet resultSet = DBUtil.select(connection, sql, null);
                while (resultSet.next()) {
                    movies.add(
                            new Movie(
                                    resultSet.getInt("id"),
                                    resultSet.getString("title"),
                                    resultSet.getInt("year"),
                                    resultSet.getInt("runtime")
                            )
                    );
                }
                DBUtil.close(connection);
                return movies;
            } catch (SQLException e) {
                throw new SQLStatmentException("SQL: " + sql);
            }
    }

    @Override
    public Movie find(int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if(resultSet.next()) {
                return new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("runtime")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            DBUtil.close(connection);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }
}
