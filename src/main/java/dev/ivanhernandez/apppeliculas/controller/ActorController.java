package dev.ivanhernandez.apppeliculas.controller;

import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorCreateWeb;
import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorDetailWeb;
import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorUpdateWeb;
import dev.ivanhernandez.apppeliculas.domain.service.ActorService;
import dev.ivanhernandez.apppeliculas.http_response.Response;
import dev.ivanhernandez.apppeliculas.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody ActorCreateWeb actorCreateWeb){
        int id = actorService.create(ActorMapper.mapper.toActor(actorCreateWeb));
        ActorDetailWeb actorDetailWeb = new ActorDetailWeb(
                id,
                actorCreateWeb.getName(),
                actorCreateWeb.getBirthYear(),
                actorCreateWeb.getDeathYear()
        );
        return new Response(actorDetailWeb);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")    
    public void update(@PathVariable("id") int id, @RequestBody ActorUpdateWeb actorUpdateWeb){
        actorUpdateWeb.setId(id);
        actorService.update(ActorMapper.mapper.toActor(actorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return new Response(ActorMapper.mapper.toActorDetailWeb(actorService.find(id)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        actorService.delete(id);
    }
}
