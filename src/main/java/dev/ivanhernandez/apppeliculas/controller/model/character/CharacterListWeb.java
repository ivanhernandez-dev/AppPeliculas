package dev.ivanhernandez.apppeliculas.controller.model.character;

import dev.ivanhernandez.apppeliculas.controller.model.actor.ActorListWeb;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterListWeb {
    int id;
    String name;
    ActorListWeb actor;
}
