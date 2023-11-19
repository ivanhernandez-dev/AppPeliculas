package dev.ivanhernandez.apppeliculas.persistence.repository.impl;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.domain.entity.Actor;
import dev.ivanhernandez.apppeliculas.domain.repository.ActorRepository;
import dev.ivanhernandez.apppeliculas.mapper.ActorMapper;
import dev.ivanhernandez.apppeliculas.persistence.dao.ActorDAO;
import dev.ivanhernandez.apppeliculas.persistence.model.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDao;

    @Override
    public int insert(Actor actor) {
        try (Connection connection = DBUtil.open(true)){
            ActorEntity actorEntity = ActorMapper.mapper.toActorEntity(actor);
            int id = actorDao.insert(connection, actorEntity);
            DBUtil.close(connection);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Actor actor) {
        try (Connection connection = DBUtil.open(true)){
            ActorEntity actorEntity = ActorMapper.mapper.toActorEntity(actor);
            actorDao.update(connection, actorEntity);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Actor> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<ActorEntity> actorEntity = actorDao.find(connection, id);
            if(actorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(ActorMapper.mapper.toActor(actorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.open(true)){
            actorDao.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
