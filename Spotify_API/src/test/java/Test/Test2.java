package Test;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Test2 {

    private static String accessToken;

    @BeforeClass
    public static void setup() {
        // Obtain the access token using Client Credentials Flow
        String clientId = "14a903a6742b4e81a0a5ad2deeee7100";
        String clientSecret = "370cbae9a96e4449a9d95f436d043066";
        String base64ClientCredentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        Response response = given()
                .header("Authorization", "Bearer " + base64ClientCredentials)
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .post("https://accounts.spotify.com/api/token");

        int statusCode = response.getStatusCode();
        System.out.println("Token Generation Status Code: " + statusCode);
        if (statusCode != 200) {
            System.out.println("Failed to obtain access token: " + response.getBody().asString());
            throw new RuntimeException("Failed to get access token");
        }

        accessToken = response.jsonPath().getString("access_token");
        System.out.println("Access Token: " + accessToken);
    }

    @Test
    public void verifyAlbumJsonStructure() {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .get("https://api.spotify.com/v1/albums/2s9rhRv31L4SIYhuqKpCnP");

        int statusCode = response.getStatusCode();
        System.out.println("Album Request Status Code: " + statusCode);
        if (statusCode != 200) {
            System.out.println("Failed to get album: " + response.getBody().asString());
            throw new RuntimeException("Failed to get album");
        }

        response.then().statusCode(200);

        String id = response.jsonPath().getString("id");
        String name = response.jsonPath().getString("name");
        assertNotNull(id);
        assertNotNull(name);
    }

    @DataProvider(name = "albumData")
    public Object[][] albumData() {
        return new Object[][]{
                {"3OZgEywV4krCZ814pTJWr7", "Dangerous Woman"},
                {"2s9rhRv31L4SIYhuqKpCnP", "Siyah"},
                {"2WW3CR8R21yjJN8xRqSleb", "Fight Kulüp 2"},
                {"2IQFXC71m1lcpg5YIy4KIF", "SEVİMSİZ"},
                {"7yjs2zcZUYTRTQym3gxioz", "Summerland"},
                {"44ra8TWI8EbdnBf1cuVMty", "Kırık Kalpler Albümü"}
        };
    }

    @Test(dataProvider = "albumData")
    public void checkAlbumNameAndIdCompatibility(String albumId, String expectedName) {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .get("https://api.spotify.com/v1/albums/" + albumId);

        int statusCode = response.getStatusCode();
        System.out.println("Album Request Status Code: " + statusCode);
        if (statusCode != 200) {
            System.out.println("Failed to get album: " + response.getBody().asString());
            throw new RuntimeException("Failed to get album");
        }

        response.then().statusCode(200);

        String name = response.jsonPath().getString("name");
        assertEquals(name, expectedName);
    }

    @DataProvider(name = "trackCountData")
    public Object[][] trackCountData() {
        return new Object[][]{
                {"2s9rhRv31L4SIYhuqKpCnP", 12},
                {"2WW3CR8R21yjJN8xRqSleb", 1},
                {"3OZgEywV4krCZ814pTJWr7", 15},
                {"2IQFXC71m1lcpg5YIy4KIF", 14},
                {"7yjs2zcZUYTRTQym3gxioz", 10},
                {"44ra8TWI8EbdnBf1cuVMty", 11}
        };
    }

    @Test(dataProvider = "trackCountData")
    public void verifyTotalCountForAlbumTracks(String albumId, int expectedCount) {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .get("https://api.spotify.com/v1/albums/" + albumId + "/tracks");

        int statusCode = response.getStatusCode();
        System.out.println("Album Tracks Request Status Code: " + statusCode);
        if (statusCode != 200) {
            System.out.println("Failed to get album tracks: " + response.getBody().asString());
            throw new RuntimeException("Failed to get album tracks");
        }

        response.then().statusCode(200);

        int totalCount = response.jsonPath().getList("items").size();
        assertEquals(totalCount, expectedCount);
    }

}
