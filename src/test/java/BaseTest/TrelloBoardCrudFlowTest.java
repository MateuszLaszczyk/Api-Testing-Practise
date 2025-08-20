package BaseTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TrelloBoardCrudFlowTest extends BaseTest {

    @Test
    public void CrudTrelloBoardTest() {

        // CREATE
        boardId = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .queryParam("name", "CRUD Demo Board")
                .when()
                .post("/boards")
                .then()
                .statusCode(200)
                .body("name", equalTo("CRUD Demo Board"))
                .extract().jsonPath().getString("id");

        System.out.println("Created Trello Board with Id: " + boardId);

        // READ
        given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when()
                .get("/boards/{id}", boardId)
                .then()
                .statusCode(200)
                .body("id", equalTo(boardId))
                .body("name", equalTo("CRUD Demo Board"));
        System.out.println("Retrieved Trello Board with Id: " + boardId);

        // UPDATE
        given()
                .contentType(ContentType.JSON)
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .queryParam("name", "CRUD Demo Updated")
                .when()
                .put("/boards/{id}", boardId)
                .then()
                .statusCode(200)
                .body("name", equalTo("CRUD Demo Updated"));
        System.out.println("Updaterd Trello Board with Id: " + boardId);

        // DELETE
        given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when()
                .delete("/boards/{id}", boardId)
                .then()
                .statusCode(200);
        System.out.println("Deleted Trello Board with Id: " + boardId);

        boardId = null;
    }
}
