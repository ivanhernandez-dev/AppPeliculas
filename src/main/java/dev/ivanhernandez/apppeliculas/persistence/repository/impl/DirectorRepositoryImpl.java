package dev.ivanhernandez.apppeliculas.persistence.repository.impl;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.domain.entity.Director;
import dev.ivanhernandez.apppeliculas.domain.repository.DirectorRepository;
import dev.ivanhernandez.apppeliculas.mapper.DirectorMapper;
import dev.ivanhernandez.apppeliculas.persistence.dao.DirectorDAO;
import dev.ivanhernandez.apppeliculas.persistence.model.DirectorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDao;

    @Override
    public int insert(Director director) {
        try (Connection connection = DBUtil.open(true)){
            DirectorEntity directorEntity = DirectorMapper.mapper.toDirectorEntity(director);
            int id = directorDao.insert(connection, directorEntity);
            DBUtil.close(connection);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Director director) {
        try (Connection connection = DBUtil.open(true)){
            DirectorEntity directorEntity = DirectorMapper.mapper.toDirectorEntity(director);
            directorDao.update(connection, directorEntity);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Director> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<DirectorEntity> directorEntity = directorDao.find(connection, id);
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.open(true)){
            directorDao.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
