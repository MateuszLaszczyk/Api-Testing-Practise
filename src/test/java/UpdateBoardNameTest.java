import BaseTest.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBoardNameTest extends BaseTest {

    @Test
    void updateBoardName() {
        String boardId = "68a5c23ae8c4ffcc97f02b1c";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .queryParam("name", "Update Board Name")
                .log().all()
                .when()
                .put("/boards/" + boardId);
    }
}
