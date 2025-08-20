import TestsInterfaces.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBoardNameTest extends BaseTest {

    @Test
    void updateBoardName() {
        boardId =
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .queryParam("name", "Update Board Name")
                .log().body()
                .when()
                .put("/boards/" + boardId)
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("id");

        System.out.println("Updated Trello Board with Id: " + boardId);
        boardId = null;

    }
}
