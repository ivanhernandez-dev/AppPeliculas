package dev.ivanhernandez.apppeliculas.mapper;


import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorCreateWeb;
import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorDetailWeb;
import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorListWeb;
import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorUpdateWeb;
import dev.ivanhernandez.apppeliculas.domain.entity.Actor;
import dev.ivanhernandez.apppeliculas.persistence.model.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    Actor toActor(ActorCreateWeb actorCreateWeb);
    Actor toActor(ActorUpdateWeb actorUpdateWeb);
    Actor toActor(ActorEntity actorEntity);
    ActorEntity toActorEntity(Actor actor);
    ActorDetailWeb toActorDetailWeb(Actor actor);
    ActorListWeb toActorListWeb(Actor actor);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;
}
