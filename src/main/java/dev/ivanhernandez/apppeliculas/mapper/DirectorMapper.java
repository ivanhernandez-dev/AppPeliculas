package dev.ivanhernandez.apppeliculas.mapper;

import dev.ivanhernandez.apppeliculas.controller.model.director.DirectorCreateWeb;
import dev.ivanhernandez.apppeliculas.controller.model.director.DirectorDetailWeb;
import dev.ivanhernandez.apppeliculas.controller.model.director.DirectorListWeb;
import dev.ivanhernandez.apppeliculas.controller.model.director.DirectorUpdateWeb;
import dev.ivanhernandez.apppeliculas.domain.entity.Director;
import dev.ivanhernandez.apppeliculas.persistence.model.DirectorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
    Director toDirector(DirectorEntity directorEntity);
    DirectorEntity toDirectorEntity(Director director);
    DirectorDetailWeb toDirectorDetailWeb(Director director);
    DirectorListWeb toDirectorListWeb(Director director);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    DirectorEntity toDirectorEntity(ResultSet resultSet) throws SQLException;
}
