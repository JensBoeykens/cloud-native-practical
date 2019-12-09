package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.contracts.CocktailId;
import com.ezgroceries.shoppinglist.contracts.CreateShoppingListInput;
import com.ezgroceries.shoppinglist.contracts.CreateShoppingListOutput;
import com.ezgroceries.shoppinglist.contracts.GetShoppingListOutput;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private CocktailService cocktailService;


    @PostMapping(value = "/shopping-lists")
    public ResponseEntity<CreateShoppingListOutput> addShoppingList(@RequestBody CreateShoppingListInput shoppingListInput) {
        ShoppingList shoppingList = new ShoppingList(shoppingListInput.getName());

        ShoppingList result = shoppingListService.create(shoppingList);

        CreateShoppingListOutput output = new CreateShoppingListOutput();
        output.setName(result.name);
        output.setShoppingListId(result.uuid);

        return new ResponseEntity(output,  HttpStatus.CREATED);
    }

    @PostMapping(value = "/shopping-lists/{uuid}/cocktails")
    public @ResponseBody List<CocktailId> addCocktails(@PathVariable UUID uuid, @RequestBody List<CocktailId> cocktails) {

        List<CocktailId> allCocktails = shoppingListService.addCocktailsToList(uuid, cocktails);

        return allCocktails;
    }

    @GetMapping(value = "/shopping-lists/{uuid}")
    public ResponseEntity<GetShoppingListOutput> getShoppingList(@PathVariable UUID uuid) {
        List<GetShoppingListOutput> shoppingLists = getShoppingLists().getBody();


        for(GetShoppingListOutput shoppingList : shoppingLists) {
            if (shoppingList.getShoppingListId().equals(uuid)) {
                return new ResponseEntity(shoppingList,  HttpStatus.OK);
            }
        }

        return new ResponseEntity(shoppingLists.get(0),  HttpStatus.OK);

    }

    @GetMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetShoppingListOutput>> getShoppingLists() {

        List<ShoppingList> shoppingLists = shoppingListService.findAll();
        List<GetShoppingListOutput> outputList =

        shoppingLists.parallelStream().map(it -> new GetShoppingListOutput(it,
                cocktailService.getIngredients(it.cocktailIds))).collect(Collectors.toList());

        return new ResponseEntity(outputList,  HttpStatus.OK);
    }

   /* private ResponseEntity<Void> entityWithLocation(Object resourceId) {

        // Determines URL of child resource based on the full URL of the given
        // request, appending the path info with the given resource Identifier
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{childId}").buildAndExpand(resourceId)
                .toUri();

        // Return an HttpEntity object - it will be used to build the
        // HttpServletResponse
        return ResponseEntity.created(location).build();
    }*/

}
