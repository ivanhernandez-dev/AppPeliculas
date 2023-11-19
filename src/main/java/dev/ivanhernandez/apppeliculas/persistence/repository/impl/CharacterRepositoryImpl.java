package dev.ivanhernandez.apppeliculas.persistence.repository.impl;

import dev.ivanhernandez.apppeliculas.db.DBUtil;
import dev.ivanhernandez.apppeliculas.domain.entity.Character;
import dev.ivanhernandez.apppeliculas.domain.repository.CharacterRepository;
import dev.ivanhernandez.apppeliculas.mapper.CharacterMapper;
import dev.ivanhernandez.apppeliculas.persistence.dao.ActorDAO;
import dev.ivanhernandez.apppeliculas.persistence.dao.CharacterDAO;
import dev.ivanhernandez.apppeliculas.persistence.model.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository {

    @Autowired
    private CharacterDAO characterDAO;
    @Autowired
    private ActorDAO actorDao;

    @Override
    public void update(Character character, int actorId) {
        try (Connection connection = DBUtil.open(true)){
            CharacterEntity characterEntity = CharacterMapper.mapper.toCharacterEntity(character);
            characterDAO.update(connection, characterEntity, actorId);
            DBUtil.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Character> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<CharacterEntity> characterEntity = characterDAO.find(connection, id);
            if(characterEntity.isEmpty()) {
                return Optional.empty();
            }
            characterEntity.get().getActorEntity(connection, actorDao);
            return Optional.of(CharacterMapper.mapper.toCharacter(characterEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
