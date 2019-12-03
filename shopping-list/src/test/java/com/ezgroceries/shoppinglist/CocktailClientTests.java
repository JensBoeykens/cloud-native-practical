package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.model.CocktailResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CocktailClientTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void listCocktails() {

		ResponseEntity<CocktailResource[]> response =
				restTemplate.getForEntity("/cocktails?search=russian", CocktailResource[].class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		assertNotNull(response.getBody());
		assertTrue(response.getBody().length > 0);

		assertEquals(response.getBody()[0].name, "Margerita");

	}
	
	
}
