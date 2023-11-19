package dev.ivanhernandez.apppeliculas.persistence.model;

import dev.ivanhernandez.apppeliculas.persistence.dao.ActorDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterEntity {

    int id;
    String name;
    ActorEntity actorEntity;

    public CharacterEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ActorEntity getActorEntity(Connection connection, ActorDAO actorDAO) {
        if (actorEntity == null) {
            actorEntity = actorDAO.findByCharacter(connection, id);
        }
        return actorEntity;
    }
}