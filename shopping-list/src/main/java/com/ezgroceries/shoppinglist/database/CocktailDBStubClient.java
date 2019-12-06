package com.ezgroceries.shoppinglist.database;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Component
@Profile("stubcocktail")
public class CocktailDBStubClient implements CocktailDBClient {

    @GetMapping(value = "search.php")
    public CocktailDBResponse searchCocktails(@RequestParam("s") String search){
        return getDummyResources();
    }

    private CocktailDBResponse getDummyResources() {
        CocktailDBResponse response = new CocktailDBResponse();

        CocktailDBResponse.DrinkResource drink1 = new CocktailDBResponse.DrinkResource();
        drink1.idDrink = "23b3d85a-3928-41c0-a533-6538a71e17c4";
        drink1.strDrink = "Margerita";
        drink1.strGlass = "Cocktail glass";
        drink1.strInstructions = "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..";
        drink1.strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg";
        drink1.strIngredient1 = "Tequila";
        drink1.strIngredient2 = "Triple sec";
        drink1.strIngredient3 = "Lime juice";
        drink1.strIngredient4 = "Salt";

        CocktailDBResponse.DrinkResource drink2 = new CocktailDBResponse.DrinkResource();
        drink2.idDrink = "d615ec78-fe93-467b-8d26-5d26d8eab073";
        drink2.strDrink = "Blue Margerita";
        drink2.strGlass = "Cocktail glass";
        drink2.strInstructions = "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..";
        drink2.strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg";
        drink2.strIngredient1 = "Tequila";
        drink2.strIngredient2 = "Blue Curacao";
        drink2.strIngredient3 = "Lime juice";
        drink2.strIngredient4 = "Salt";

        response.setDrinks(Arrays.asList(
                drink1,
                drink2));

        return response;
    }

}
