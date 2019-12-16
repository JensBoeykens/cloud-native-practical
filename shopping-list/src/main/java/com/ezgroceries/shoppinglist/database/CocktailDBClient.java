package com.ezgroceries.shoppinglist.database;

import com.ezgroceries.shoppinglist.database.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.database.repository.CocktailRepository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("realcocktail")
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", fallback = CocktailDBClient.CocktailDBClientFallback.class)
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

    @Component
    class CocktailDBClientFallback implements CocktailDBClient {

        private final CocktailRepository cocktailRepository;

        public CocktailDBClientFallback(CocktailRepository cocktailRepository) {
            this.cocktailRepository = cocktailRepository;
        }

        @Override
        public CocktailDBResponse searchCocktails(String search) {
            List<CocktailEntity> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

            CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
            cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktailEntity -> {
                CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
                drinkResource.setIdDrink(cocktailEntity.getIdDrink());
                drinkResource.setStrDrink(cocktailEntity.getName());
                String[] ingredients = cocktailEntity.getIngredients().toArray(new String[0]);
                if (ingredients.length >= 1)
                    drinkResource.setStrIngredient1(ingredients[0]);
                if (ingredients.length >= 2)
                    drinkResource.setStrIngredient1(ingredients[1]);
                if (ingredients.length >= 3)
                    drinkResource.setStrIngredient1(ingredients[2]);
                if (ingredients.length >= 4)
                    drinkResource.setStrIngredient1(ingredients[3]);
                if (ingredients.length >= 5)
                    drinkResource.setStrIngredient1(ingredients[4]);
                if (ingredients.length >= 6)
                    drinkResource.setStrIngredient1(ingredients[5]);
                if (ingredients.length >= 7)
                    drinkResource.setStrIngredient1(ingredients[6]);
                return drinkResource;
            }).collect(Collectors.toList()));

            return cocktailDBResponse;
        }
    }

}
