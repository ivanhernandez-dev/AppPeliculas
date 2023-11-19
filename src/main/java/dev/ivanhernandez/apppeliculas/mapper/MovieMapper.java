package dev.ivanhernandez.apppeliculas.mapper;

import dev.ivanhernandez.apppeliculas.controller.model.movie.MovieCreateWeb;
import dev.ivanhernandez.apppeliculas.controller.model.movie.MovieDetailWeb;
import dev.ivanhernandez.apppeliculas.controller.model.movie.MovieListWeb;
import dev.ivanhernandez.apppeliculas.controller.model.movie.MovieUpdateWeb;
import dev.ivanhernandez.apppeliculas.domain.entity.Movie;
import dev.ivanhernandez.apppeliculas.persistence.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characters", expression = "java(movieEntity.getCharacterEntities().stream().map(characterEntity -> CharacterMapper.mapper.toCharacter(characterEntity)).toList())")
    Movie toMovie(MovieEntity movieEntity);
    MovieListWeb toMovieListWeb(Movie movie);
    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirectorListWeb(movie.getDirector()))")
    @Mapping(target = "characters", expression = "java(movie.getCharacters().stream().map(character -> CharacterMapper.mapper.toCharacterListWeb(character)).toList())")
    MovieDetailWeb toMovieDetailWeb(Movie movie);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;
    MovieEntity toMovieEntity(Movie movie);
    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characters", ignore = true)
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "characters", ignore = true)
    Movie toMovie(MovieUpdateWeb movieUpdateWeb);
}
