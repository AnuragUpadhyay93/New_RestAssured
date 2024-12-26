package com.Notes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GraphQL_QueryTest {
	
		@Test(priority=1,enabled=false)
		public void getAllFilmsTest()
		{
			RestAssured.baseURI = "https://swapi-graphql.netlify.app";
			String query = "{\"query\":\"{\\n  allFilms(first: 2) {\\n    films {\\n      created\\n      director\\n      title\\n    }\\n  }\\n}\"}";
			given().log().all()
				.contentType("application/json")
				.body(query)
				.when().log().all()
				.post("/.netlify/functions/index")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("data.allFilms.films[0].director", equalTo("George Lucas"));
			
		}

		
		@Test(priority=2,enabled=false)
		public void postPokemonAPI()
		{
			RestAssured.baseURI = "https://beta.pokeapi.co";
//			String query = "{\"query\":\"{\n" +
//					"  \"pokemon_v2_pokemon\": {\n" +
//					"    \"limit\": 10,\n" +
//					"    \"height\": \"someHeight\",\n" +
//					"    \"id\": \"someId\",\n" +
//					"    \"name\": \"someName\",\n" +
//					"    \"order\": \"someOrder\",\n" +
//					"    \"pokemon_species_id\": \"someSpeciesId\"\n" +
//					"  }\n" +
//					"}";


					
			String query = "{\"query\":\"{\\n  pokemon_v2_pokemon(limit: 2) {\\n    height\\n    id\\n    name\\n    order\\n    pokemon_species_id\\n  }\\n}\"}";
			given().log().all()
				.contentType("application/json")
				.body(query)
				.when().log().all()
				.post("/graphql/v1beta")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("data.pokemon_v2_pokemon[4].name", equalTo("charmeleon"));
									
		}
		
		@Test(priority = 3, enabled = true)
		public void getPokemonDetails() {
			RestAssured.baseURI = "https://beta.pokeapi.co";
			String query = "{\"query\":\"{\\n  pokemon_v2_pokemon(limit: 3) {\\n    height\\n    id\\n    name\\n    order\\n    pokemon_species_id\\n  }\\n}\"}";
			given().log().all()
			.contentType("application/json").body(query).when().log().all()
					.post("/graphql/v1beta").then().log().all().assertThat().statusCode(200)
					.and().body("data.pokemon_v2_pokemon[2].name", equalTo("venusaur"))
					.and().body("data.pokemon_v2_pokemon[1].height", equalTo(10));

		}
	  
	}



