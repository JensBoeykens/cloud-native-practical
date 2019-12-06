package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.database.CocktailDBClient;
import com.ezgroceries.shoppinglist.database.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.CocktailResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    @Autowired
    private CocktailDBClient cocktailDBClient;

    @GetMapping
    public List<CocktailResource> get(@RequestParam String search) {

        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);

        return cocktailDBResponse.getDrinks().stream().map(drink -> new CocktailResource(
                UUID.randomUUID(),
                drink.strDrink,
                drink.strGlass,
                drink.strInstructions,
                drink.strDrinkThumb,
                drink.getIngredients()
        )).collect(Collectors.toList());
    }

}
