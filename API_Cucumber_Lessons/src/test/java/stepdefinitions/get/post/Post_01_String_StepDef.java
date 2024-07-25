package stepdefinitions.get.post;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static baseUrl.Reqres_BaseUrl.specification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post_01_String_StepDef {
    /*
       Given
           1) https://reqres.in/api/users
           2) {
               "name": "Hakan Kara",
               "job": "leader"
               }
       When
           I send POST Request to the Url
       Then
           Status code is 201
           And response body should be like {
                                               "name": "Hakan Kara",
                                               "job": "leader",
                                               "id": "354",
                                               "createdAt": "2024-02-06T09:20:25.174Z"
                                             }
    */
    Response response;
    @Given("kullanici {string} sitesine gider")
    public void kullaniciSitesineGider(String arg0) {
        //1- set the url
        specification.pathParam("first", "users"); //pathParam("first", "users") => end point


    }

    @And("post islemi yapar")
    public void postIslemiYapar() {
        // 2- set the expected data
        String payload="{\n" +
                "    \"name\": \"Hakan Kara\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        //3- send the request and get the response
        response= given(specification).body(payload).post("{first}");
        response.prettyPrint();

    }

    @Then("response dogrulanir")
    public void responseDogrulanir() {
        //4- do assertion
        assertEquals(201,response.statusCode() );

        String expectedName= "Hakan Kara";
        String expectedJob="leader";

        JsonPath jsonPath =response.jsonPath();
        String actualName= jsonPath.getString("name");
        String actualJob= jsonPath.getString("job");

        assertEquals(expectedName, actualName);
        assertEquals(expectedJob, actualJob);



    }
}
