import BaseTest.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateListOnBoardTest extends BaseTest {

    @Test
    @DisplayName("This test create a list on a board")
    public void createListOnBoardTest() {
        String listId = given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("name", "To Do")
                .when()
                .post("/boards/{id}/lists", boardId)
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("id");
    }
}
