package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.contracts.CocktailId;
import com.ezgroceries.shoppinglist.contracts.CreateShoppingListOutput;
import com.ezgroceries.shoppinglist.contracts.GetShoppingListOutput;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingListClientTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void listShoppingLists() {

        ResponseEntity<GetShoppingListOutput[]> shoppingLists = restTemplate.getForEntity("/shopping-lists", GetShoppingListOutput[].class);

        assertNotNull(shoppingLists.getBody());
        assertTrue(shoppingLists.getBody().length > 0);
        assertEquals(shoppingLists.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void createShoppingList() {

        // use a unique number to avoid conflicts
        String name = "list birthday";
        ShoppingList shoppingList = new ShoppingList(name);

        ResponseEntity<CreateShoppingListOutput> responseEntity = restTemplate.postForEntity("/shopping-lists", shoppingList, CreateShoppingListOutput.class);


        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("list birthday", responseEntity.getBody().getName());
        assertNotNull(responseEntity.getBody().getShoppingListId());

    }

    @Test
    public void getShoppingList() {

        ResponseEntity<GetShoppingListOutput> shoppingList = restTemplate.getForEntity("/shopping-lists/" + UUID.randomUUID(), GetShoppingListOutput.class);

        assertNotNull(shoppingList.getBody());
        assertEquals(shoppingList.getBody().getName(), "Stephanies birthday");
        assertNotNull(shoppingList.getBody().getShoppingListId());
        assertNotNull(shoppingList.getBody().getIngredients());
        assertTrue(shoppingList.getBody().getIngredients().length > 0);

        assertEquals(shoppingList.getStatusCode(), HttpStatus.OK);

    }


    @Test
    public void addCocktailToShoppingList() {

        List<CocktailId> input = Arrays.asList(new CocktailId(UUID.randomUUID().toString()),
                new CocktailId(UUID.randomUUID().toString()));
       // input.setCocktailIDs(Arrays.asList(new AddCocktailsInput.CocktailId(UUID.randomUUID().toString()),
       //         new AddCocktailsInput.CocktailId(UUID.randomUUID().toString())));

        ResponseEntity<CocktailId[]> shoppingList = restTemplate.postForEntity("/shopping-lists/" + UUID.randomUUID() + "/cocktails", input, CocktailId[].class);

        assertNotNull(shoppingList.getBody());
        assertTrue(shoppingList.getBody().length == 2);

        assertEquals(shoppingList.getStatusCode(), HttpStatus.OK);

    }

}
