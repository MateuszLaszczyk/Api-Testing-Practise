package BaseTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class CreateNewTrelloBoardTest extends BaseTest {


    @Test
    void createAndDeleteBoard() {
        boardId =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .queryParam("key", trelloKey)
                        .queryParam("token", trelloToken)
                        .queryParam("name", "Rest Assured Trello Board")
                        .log().all()
                        .when()
                        .post("/boards")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract()
                        .jsonPath()
                        .getString("id");

        System.out.println("Created boardId = " + boardId);
    }


}
