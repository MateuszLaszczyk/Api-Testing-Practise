package DeleteAllBoards;

import BaseTest.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;


import static io.restassured.RestAssured.given;

public class RemoveAllBoards extends BaseTest {

        @Test
        void cleanup(){
        // get all boards
        List<String> boardIds =
                given()
                        .queryParam("key", trelloKey)
                        .queryParam("token", trelloToken)
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/members/me/boards")
                        .then()
                        .extract()
                        .jsonPath()
                        .getList("id"); // every id

            // removing every board
        for (String id : boardIds) {
            given()
                    .queryParam("key", trelloKey)
                    .queryParam("token", trelloToken)
                    .when()
                    .delete("/boards/{id}", id)
                    .then();

            System.out.println("Deleted board: " + id);
        }
    }
}

