package com.ezgroceries.shoppinglist.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@IdClass(value = CocktailShoppingListId.class)
@Table(name = "cocktail_shopping_list")
public class CocktailShoppingListEntity implements Serializable {

    @Id
    @Column(name="COCKTAIL_ID")
    private UUID cocktailId;

    @Id
    @Column(name="SHOPPING_LIST_ID")
    private UUID shoppingListId;


    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
