package com.ezgroceries.shoppinglist.model;

import java.util.List;
import java.util.UUID;


public class CocktailResource {

    public UUID uuid;
    public String name;
    public String glass;
    public String instructions;
    public String url;
    public List<String> ingredients;

    public CocktailResource() {

    }

    public CocktailResource(UUID uuid,
                            String name,
                            String glass,
                            String instructions,
                            String url,
                            List<String> ingredients) {

        this.uuid = uuid;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.url = url;
        this.ingredients = ingredients;

    }


    public String toString() {
        return uuid + " " + name;
    }


}
