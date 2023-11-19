package dev.ivanhernandez.apppeliculas.domain.entity;

public class Character {
    int id;
    String name;
    Actor actor;

    public Character(){

    }

    public Character(int id, String name, Actor actor){
        this.id = id;
        this.name = name;
        this.actor = actor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
