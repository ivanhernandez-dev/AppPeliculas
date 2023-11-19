package dev.ivanhernandez.apppeliculas.controller.model.character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterUpdateWeb {
    int id;
    String name;
    int actorId;
}
