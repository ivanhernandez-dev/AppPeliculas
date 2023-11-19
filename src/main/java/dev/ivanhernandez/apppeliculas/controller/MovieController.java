package dev.ivanhernandez.apppeliculas.controller;

import dev.ivanhernandez.apppeliculas.controller.model.character.CharacterCreateWeb;
import dev.ivanhernandez.apppeliculas.controller.model.character.CharacterUpdateWeb;
import dev.ivanhernandez.apppeliculas.controller.model.movie.MovieCreateWeb;
import dev.ivanhernandez.apppeliculas.controller.model.movie.MovieListWeb;
import dev.ivanhernandez.apppeliculas.controller.model.movie.MovieUpdateWeb;
import dev.ivanhernandez.apppeliculas.domain.entity.Movie;
import dev.ivanhernandez.apppeliculas.domain.service.MovieService;
import dev.ivanhernandez.apppeliculas.http_response.Response;
import dev.ivanhernandez.apppeliculas.mapper.CharacterMapper;
import dev.ivanhernandez.apppeliculas.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Value("${default.page.size}")
    private Integer LIMIT;
    @Value("${application.url}")
    private String urlBase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : LIMIT;
        List<Movie> movies = (pageSize != null)? movieService.getAll(page, pageSize) : movieService.getAll();
        List<MovieListWeb> moviesWeb = movies.stream()
                .map(movie -> MovieMapper.mapper.toMovieListWeb(movie))
                .toList();
        int totalRecords = movieService.getTotalNumberOfRecords();

        Response response = new Response(moviesWeb, totalRecords);
        if(page != null){
            response.paginate(page, pageSize, urlBase);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return new Response(MovieMapper.mapper.toMovieDetailWeb(movieService.find(id)));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWeb){
        int id = movieService.create(MovieMapper.mapper.toMovie(movieCreateWeb), movieCreateWeb.getDirectorId(), movieCreateWeb.getCharacters());
        return new Response(MovieMapper.mapper.toMovieDetailWeb(movieService.find(id)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody MovieUpdateWeb movieUpdateWeb){
        movieUpdateWeb.setId(id);
        movieService.update(MovieMapper.mapper.toMovie(movieUpdateWeb), movieUpdateWeb.getDirectorId());
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(@PathVariable("idMovie") int idMovie, @PathVariable("idCharacter") int idCharacter){
        movieService.deleteCharacter(idMovie, idCharacter);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam("id") int id){
        movieService.delete(id);
    }

    @PostMapping("/{idMovie}/characters")
    @ResponseStatus(HttpStatus.CREATED)
    public Response addCharacter(@PathVariable("idMovie") int idMovie, @RequestBody CharacterCreateWeb characterCreateWeb){
        movieService.addCharacter(CharacterMapper.mapper.toCharacter(characterCreateWeb), characterCreateWeb.getActorId(), idMovie);
        return new Response(MovieMapper.mapper.toMovieDetailWeb(movieService.find(idMovie)));
    }
}
