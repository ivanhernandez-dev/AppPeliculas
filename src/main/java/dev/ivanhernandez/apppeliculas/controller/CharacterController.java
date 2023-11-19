package dev.ivanhernandez.apppeliculas.controller;

import dev.ivanhernandez.apppeliculas.controller.model.character.CharacterListWeb;
import dev.ivanhernandez.apppeliculas.controller.model.character.CharacterUpdateWeb;
import dev.ivanhernandez.apppeliculas.domain.service.CharacterService;
import dev.ivanhernandez.apppeliculas.http_response.Response;
import dev.ivanhernandez.apppeliculas.mapper.CharacterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") int id, @RequestBody CharacterUpdateWeb characterUpdateWeb){
        characterUpdateWeb.setId(id);
        characterService.update(CharacterMapper.mapper.toCharacter(characterUpdateWeb), characterUpdateWeb.getActorId());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response find(@PathVariable("id") int id){
        return new Response(CharacterMapper.mapper.toCharacterListWeb(characterService.find(id)));
    }
}
