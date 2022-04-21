package com.tqs.lab7_1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class AppTest 
{
    String baseUrl = "https://jsonplaceholder.typicode.com/todos";

    @Test
    public void testAllToDos()
    {
        given().when().get(baseUrl).then().assertThat().statusCode(200);
    }

    @Test
    public void testToDo4(){
        given().when().get(baseUrl + "/4").then().assertThat().statusCode(200).and().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void listingAllToDos(){
        given().when().get(baseUrl).then().assertThat().statusCode(200).and().body("id", hasItems(198, 199));
    }
}
