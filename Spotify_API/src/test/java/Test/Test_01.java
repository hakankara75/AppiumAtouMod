package Test;

import BaseUrl.SpotifyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Test_01 extends SpotifyBaseUrl {


    @Test
    public void testName() {

        //1- Set the URL
        spec.pathParam("first", "3OZgEywV4krCZ814pTJWr7");

        // 2-       Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //3- Do assertion
//        response.then()
//                .statusCode(200)
//                .contentType(ContentType.JSON)
//                .body("postId", hasItem(3),
//                        "id", hasItem(11),
//                        "name", hasItem("fugit labore quia mollitia quas deserunt nostrum sunt"),
//                        "email", hasItem("Veronica_Goodwin@timmothy.net"),
//                        "body", hasItem("ut dolorum nostrum id quia aut est\nfuga est inventore vel eligendi explicabo quis consectetur\naut occaecati repellat id natus quo est\nut blanditiis quia ut vel ut maiores ea"));


    }
}
