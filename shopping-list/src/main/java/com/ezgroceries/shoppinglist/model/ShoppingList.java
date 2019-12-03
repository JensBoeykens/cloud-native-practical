package com.ezgroceries.shoppinglist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingList {

    public UUID uuid;
    public String name;
    public List<UUID> cocktailIds;

    public ShoppingList() {
        cocktailIds = new ArrayList<UUID>();
    }

    public ShoppingList(
                        String name
                       ) {

        this.name = name;
        uuid = UUID.randomUUID();
        cocktailIds = new ArrayList<UUID>();
    }




}
