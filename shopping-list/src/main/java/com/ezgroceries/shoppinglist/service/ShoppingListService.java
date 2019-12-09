package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.contracts.CocktailId;
import com.ezgroceries.shoppinglist.database.entities.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.database.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.database.repository.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.database.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailShoppingListRepository cocktailShoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository,
                               CocktailShoppingListRepository cocktailShoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailShoppingListRepository = cocktailShoppingListRepository;
    }

    public ShoppingList create(ShoppingList shoppingList) {

        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(shoppingList.uuid);
        shoppingListEntity.setName(shoppingList.name);
        ShoppingListEntity createdShoppingList = shoppingListRepository.save(shoppingListEntity);

        ShoppingList result = new ShoppingList(createdShoppingList.getName());
        result.uuid = createdShoppingList.getId();

        return result;
    }

    public List<CocktailId> addCocktailsToList(UUID shoppingList, List<CocktailId> cocktails){

        List<String> existingCocktails =
                cocktailShoppingListRepository.findByShoppingListId(shoppingList)
                        .stream().map(entity -> entity.getCocktailId().toString()).collect(Collectors.toList());

        for (CocktailId cocktailId : cocktails) {
            if (!existingCocktails.contains(cocktailId.cocktailId)) {
                CocktailShoppingListEntity entity = new CocktailShoppingListEntity();
                entity.setCocktailId(UUID.fromString(cocktailId.cocktailId));
                entity.setShoppingListId(shoppingList);
                cocktailShoppingListRepository.save(entity);
            }
        }

        return cocktailShoppingListRepository.findByShoppingListId(shoppingList).stream().map(
                entity -> new CocktailId(entity.getCocktailId().toString())
        ).collect(Collectors.toList());
    }

    public List<ShoppingList> findAll(){
        List<ShoppingList> shoppingLists = new ArrayList<>();
        Iterable<ShoppingListEntity> it = shoppingListRepository.findAll();
        it.forEach(s -> {
            ShoppingList newS = new ShoppingList(s.getName());
            newS.uuid = s.getId();

            // add cocktails
            newS.cocktailIds =
                    cocktailShoppingListRepository.findByShoppingListId(newS.uuid).stream().map(e -> e.getCocktailId()).collect(Collectors.toList());

            shoppingLists.add(newS);
        });



        return shoppingLists;
    }

}
