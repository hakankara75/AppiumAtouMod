package stepdefinitions.get.post;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utilities.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static baseUrl.Reqres_BaseUrl.specification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post_04_ObjectMapper_String_Map_Abbas {
    /*
GIVEN
https://reqres.in/api/register
{
    "email": "eve.holt@reqres.in",
    "password": "pistol"
}
WHEN
I send PUT request to the URL
THEN
Status code should be 200
And response body should be
{
    "id": 4,
    "token": "QpwL5tke4Pnpja7X4"
}
*/
    Map<String , Object> payloadData;
    Response response;

    @Given("kullanici https:\\/\\/reqres.in\\/api\\/ sitesine gider")
    public void kullaniciHttpsReqresInApiSitesineGider() {
        //1- set the url
        specification.pathParam("first", "register");
    }

    @And("kullanici register icin post islemi yapar")
    public void kullaniciRegisterIcinPostIslemiYapar() {
        //2- set the expected data

        String payload="{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";


        payloadData= ObjectMapperUtils.convertData(payload, HashMap.class);

        //3- send the request and get the response
        response =given(specification).body(payloadData).post("{first}");
        response.prettyPrint();
    }

    @Then("kullanici register islemi icin gelen response dogrular")
    public void kullaniciRegisterIslemiIcinGelenResponseDogrular() {
        Map<String , Object> actualData= ObjectMapperUtils.convertData(response.asString(), HashMap.class);
        Integer expectedId=4;
        String expectedToken= "QpwL5tke4Pnpja7X4";
        //4- do assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedId, (actualData.get("id")));
        assertEquals(expectedToken, (actualData.get("token")));

    }

}
