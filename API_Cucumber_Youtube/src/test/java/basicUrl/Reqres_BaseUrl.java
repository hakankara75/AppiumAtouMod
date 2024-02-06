package basicUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class Reqres_BaseUrl {
    public static RequestSpecification specification;
    public static void setup(){
        specification= new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri("https://reqres.in/api").build();
    }
}