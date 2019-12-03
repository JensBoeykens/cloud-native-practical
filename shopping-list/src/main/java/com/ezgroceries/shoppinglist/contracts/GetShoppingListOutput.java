package com.ezgroceries.shoppinglist.contracts;

import com.ezgroceries.shoppinglist.model.ShoppingList;

import java.util.UUID;

public class GetShoppingListOutput {

    private UUID shoppingListId;

    private String name;

    private String[] ingredients;

    public GetShoppingListOutput() {

    }

    public GetShoppingListOutput(ShoppingList shoppingList, String[] ingredients) {
        this.name = shoppingList.name;
        this.shoppingListId = shoppingList.uuid;
        this.ingredients = ingredients;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
