import TestsInterfaces.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteTrelloBoardTest extends BaseTest {


    @Test
    @Tag("Destructive")
    void deleteBoard_removesResource_andSubsequentGetIs404() {
        // arrange: create
        boardId = given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("name", "Delete Test Board")
                .when().post("/boards")
                .then().statusCode(200)
                .extract().jsonPath().getString("id");

        // act: delete
        given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when().delete("/boards/{id}", boardId)
                .then().statusCode(200);

        // assert: GET after delete 404
        given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when().get("/boards/{id}", boardId)
                .then().statusCode(404);

        boardId = null;
    }

}


