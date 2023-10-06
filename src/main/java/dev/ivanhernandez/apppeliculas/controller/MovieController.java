package dev.ivanhernandez.apppeliculas.controller;

import dev.ivanhernandez.apppeliculas.domain.entity.Movie;
import dev.ivanhernandez.apppeliculas.domain.service.MovieService;
import dev.ivanhernandez.apppeliculas.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Value("${default.pageSize}")
    private int defaultPageSize;

    @GetMapping("")
    public Response getAll(
            @RequestParam(name = "page", required = false) Optional<Integer> page,
            @RequestParam(name = "pageSize", required = false) Optional<Integer> pageSize) {
        int actualPageSize = pageSize.orElse(defaultPageSize);
        int totalNumberOfRecords = movieService.getTotalNumberOfRecords();
        return new Response(movieService.getAll(page, actualPageSize), totalNumberOfRecords, page, actualPageSize);
    }

    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return new Response(movieService.find(id));
    }
}