package dev.ivanhernandez.apppeliculas.mapper;

import dev.ivanhernandez.apppeliculas.controller.model.character.CharacterCreateWeb;
import dev.ivanhernandez.apppeliculas.controller.model.character.CharacterListWeb;
import dev.ivanhernandez.apppeliculas.controller.model.character.CharacterUpdateWeb;
import dev.ivanhernandez.apppeliculas.persistence.model.CharacterEntity;
import dev.ivanhernandez.apppeliculas.domain.entity.Character;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"characters\"))")
    CharacterEntity toCharacterEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "actor" , expression = "java(ActorMapper.mapper.toActor(characterEntity.getActorEntity()))")
    Character toCharacter(CharacterEntity characterEntity);

    @Mapping(target = "actor" , expression = "java(ActorMapper.mapper.toActorListWeb(character.getActor()))")
    CharacterListWeb toCharacterListWeb(Character character);

    @Mapping(target = "actor", ignore = true)
    Character toCharacter(CharacterUpdateWeb characterUpdateWeb);

    CharacterEntity toCharacterEntity(Character character);

    @Mapping(target = "actor", ignore = true)
    Character toCharacter(CharacterCreateWeb characterCreateWeb);
}
