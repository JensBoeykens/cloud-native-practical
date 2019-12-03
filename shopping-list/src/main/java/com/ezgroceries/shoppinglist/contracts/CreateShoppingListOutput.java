package com.ezgroceries.shoppinglist.contracts;

import java.util.UUID;

public class CreateShoppingListOutput {

    private UUID shoppingListId;

    private String name;

    public CreateShoppingListOutput() {

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
}
