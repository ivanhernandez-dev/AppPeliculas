package dev.ivanhernandez.apppeliculas.controller.model.character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCreateWeb {
    String name;
    int actorId;
}
