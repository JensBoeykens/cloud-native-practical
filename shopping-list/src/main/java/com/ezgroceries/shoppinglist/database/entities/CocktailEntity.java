package com.ezgroceries.shoppinglist.database.entities;

import com.ezgroceries.shoppinglist.util.StringSetConverter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @Column(name="ID")
    private UUID id;

    @Column(name="ID_DRINK")
    private String idDrink;

    @Column(name="NAME")
    private String name;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public static String getIdDrink(CocktailEntity entity) {
        return entity.idDrink;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<String> getIngredients() {
        return this.ingredients;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public String getName() {
        return name;
    }
}
