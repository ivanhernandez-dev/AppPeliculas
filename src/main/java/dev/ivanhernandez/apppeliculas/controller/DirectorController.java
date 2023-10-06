package dev.ivanhernandez.apppeliculas.controller;

import dev.ivanhernandez.apppeliculas.domain.entity.Director;
import dev.ivanhernandez.apppeliculas.domain.service.DirectorService;
import dev.ivanhernandez.apppeliculas.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response insert(@RequestBody Director director) {
        director.setId(directorService.insert(director));
        return new Response(director);
    }

    @GetMapping("/{id}")
    public Director find(@PathVariable("id") int id) {
        return directorService.find(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping()
    public void update(@RequestBody Director director) {
        directorService.update(director);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }
}