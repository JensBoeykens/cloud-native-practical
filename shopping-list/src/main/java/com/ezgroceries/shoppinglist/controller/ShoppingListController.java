package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.contracts.CocktailId;
import com.ezgroceries.shoppinglist.contracts.CreateShoppingListInput;
import com.ezgroceries.shoppinglist.contracts.CreateShoppingListOutput;
import com.ezgroceries.shoppinglist.contracts.GetShoppingListOutput;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ShoppingListController {

    @PostMapping(value = "/shopping-lists")
    public ResponseEntity<CreateShoppingListOutput> addShoppingList(@RequestBody CreateShoppingListInput shoppingListInput) {
        ShoppingList shoppingList = new ShoppingList(shoppingListInput.getName());

        CreateShoppingListOutput output = new CreateShoppingListOutput();
        output.setName(shoppingList.name);
        output.setShoppingListId(shoppingList.uuid);

        return new ResponseEntity(output,  HttpStatus.CREATED);
    }

    @PostMapping(value = "/shopping-lists/{uuid}/cocktails")
    public @ResponseBody List<CocktailId> addCocktails(@PathVariable UUID uuid, @RequestBody List<CocktailId> cocktails) {

        /*ShoppingList shoppingList = getShoppingList(uuid).getBody();

        for (CocktailId id : cocktails) {
            shoppingList.cocktailIds.add(UUID.fromString(id.cocktailId));
        }*/

        return cocktails;
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

        List<ShoppingList> shoppingLists = getDummyResources();
        List<GetShoppingListOutput> outputList =

        shoppingLists.parallelStream().map(it -> new GetShoppingListOutput(it, new String[]{"Tequila",
                "Triple sec",
                "Lime juice",
                "Salt",
                "Blue Curacao"})).collect(Collectors.toList());

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

    private List<ShoppingList> getDummyResources() {
        ShoppingList s1 = new ShoppingList("Stephanies birthday");
        s1.cocktailIds.add(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"));
        s1.cocktailIds.add(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"));

        return Arrays.asList(s1,
                new ShoppingList("Alexs birthday"));
    }

}
