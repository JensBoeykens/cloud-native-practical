package com.ezgroceries.shoppinglist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("stubcocktail")
@AutoConfigureMockMvc
@ComponentScan
public class MockMvcTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getCocktailsTest() throws Exception {
		
		this.mockMvc //
				.perform(get("/cocktails?search=russian") //
						.accept(MediaType.parseMediaType("application/json")
						)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("[0].name").value("Margerita"));
	}

}
