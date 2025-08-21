import TestsInterfaces.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TrelloBoardCrudFlowTest extends BaseTest {

    @Test
    public void CrudTrelloBoardTest() {
        String name = "Rest Assured Trello Board";
        boardId =
                // READ
                given()
                        .queryParam("key", trelloKey)
                        .queryParam("token", trelloToken)
                        .when()
                        .get("/boards/{id}", boardId)
                        .then()
                        .statusCode(200)
                        .body("id", equalTo(boardId))
                        .body("name", equalTo(name))
                        .extract().jsonPath().getString("id");
        System.out.println("Retrieved Trello Board with Id: " + boardId);

        // UPDATE
        boardId =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("key", trelloKey)
                        .queryParam("token", trelloToken)
                        .queryParam("name", "CRUD Demo Updated")
                        .when()
                        .put("/boards/{id}", boardId)
                        .then()
                        .statusCode(200)
                        .body("name", equalTo("CRUD Demo Updated"))
                        .extract().jsonPath().getString("id");
        System.out.println("Updated Trello Board with Id: " + boardId);

    }
}
