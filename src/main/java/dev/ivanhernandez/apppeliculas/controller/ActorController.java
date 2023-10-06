package dev.ivanhernandez.apppeliculas.controller;

import dev.ivanhernandez.apppeliculas.domain.entity.Actor;
import dev.ivanhernandez.apppeliculas.domain.service.ActorService;
import dev.ivanhernandez.apppeliculas.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response insert(@RequestBody Actor actor) {
        actor.setId(actorService.insert(actor));
        return new Response(actor);
    }

    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return new Response(actorService.find(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping()
    public void update(@RequestBody Actor actor) {
        actorService.update(actor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        actorService.delete(id);
    }
}
