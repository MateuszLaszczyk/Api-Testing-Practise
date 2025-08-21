import BaseTest.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class InivteMemberToBoardViaEmailTest extends BaseTest {

    @Test
    public void inviteMemberToBoardViaEmail() {
            given()
                    .queryParam("key", trelloKey)
                    .queryParam("token", trelloToken)
                    .queryParam("type", "normal")
                    .queryParam("email", email)
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .when()
                    .put("/boards/{id}/members", boardId)
                    .then()
                    .statusCode(200);


        }
    }
