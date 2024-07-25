package BaseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


import static io.restassured.RestAssured.given;


public class SpotifyBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setup(){

        spec= new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer ="+generateToken()) //post islemine kullanma
                .setContentType("application/x-www-form-urlencoded")
                .addFormParam("grant_type", "client_credentials")
                .setBaseUri("https://accounts.spotify.com/api/token")
                .build();

    }

    public static String generateToken(){
        String bodyMap = "{ \"client_id\" : \"14a903a6742b4e81a0a5ad2deeee7100\", \"client_secret\" : \"370cbae9a96e4449a9d95f436d043066\" }";

        //   Map<String, String> bodyMap= new HashMap<>();  //istersek bu da calisir String yerine
//     bodyMap.put("username", "admin" );
//     bodyMap.put("password", "password123" );

        Response response= given().contentType(ContentType.JSON).body(bodyMap).post("https://accounts.spotify.com/api/token");

        return response.jsonPath().getString("token");

    }
}
