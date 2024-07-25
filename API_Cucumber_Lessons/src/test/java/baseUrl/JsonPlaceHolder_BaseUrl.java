package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class JsonPlaceHolder_BaseUrl {

    public static RequestSpecification specJsonPlaceHolder;
    public static void setUpJsonPlaceHolder(){
        specJsonPlaceHolder= new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri("https://jsonplaceholder.typicode.com/")
                .build();



    }
}
