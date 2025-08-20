import TestsInterfaces.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateListOnBoardTest extends BaseTest {

    @Test
    public void createAlistOnBoardTest() {
        String listId = given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("name", "To Do")
                .when()
                .post("/boards/{id}/lists","68a5f4d49ead799e672fb859" )
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("id");
    }
}
