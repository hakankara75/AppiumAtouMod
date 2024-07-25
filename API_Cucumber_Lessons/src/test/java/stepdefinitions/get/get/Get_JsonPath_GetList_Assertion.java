package stepdefinitions.get.get;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static baseUrl.JsonPlaceHolder_BaseUrl.specJsonPlaceHolder;
import static io.restassured.RestAssured.given;

public class Get_JsonPath_GetList_Assertion {
     /*
      Given
          https://jsonplaceholder.typicode.com/posts/3/comments
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
And
    Response format should be “application/json”
And
    “email” is “Veronica_Goodwin@timmothy.net”,
And
    “name” is "fugit labore quia mollitia quas deserunt nostrum sunt",
And
    “postId” is 3
     "id" is 11,
     And
    "body" is "ut dolorum nostrum id quia aut est\nfuga est inventore vel eligendi explicabo quis consectetur\naut occaecati repellat id natus quo est\nut blanditiis quia ut vel ut maiores ea"
   */

    Response response;
    @Given("jsonplaceholder sitesine gidilir")
    public void jsonplaceholderSitesineGidilir() {
        //1- set the url
        specJsonPlaceHolder.pathParams("a", "posts", "b", 3, "c", "comments");

    }

    @And("get istegi yapilir")
    public void getIstegiYapilir() {
        //2- set the expected data

        //3- send the request and get the response
        response=given(specJsonPlaceHolder).get("{a}/{b}/{c}");
        response.prettyPrint();
    }

    @Then("body assert edilir")
    public void bodyAssertEdilir() {
        //4- do assertion
        response.then()
                .statusCode(200)
                .contentType("application/json");

        JsonPath jsonPath =response.jsonPath();


    }

}
