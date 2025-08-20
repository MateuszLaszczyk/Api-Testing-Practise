package BaseTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteTrelloBoardTest extends BaseTest {


    @Test
    public void deleteTrelloBoard() {
        String boardId = "651c3f073cf46a4120e38571";
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .queryParam("token", trelloToken)
                        .queryParam("key", trelloKey)
                        .when()
                        .delete("/boards/" + boardId)
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract()
                        .jsonPath().getString("id");

    }

}


