import BaseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBoardNameTest extends BaseTest {

    @Test
    @Epic("Trello API")
    @Feature("Board Management")
    @DisplayName("Update board name")
    @Description("Verifies that an existing board's name can be successfully updated.")
    @Severity(SeverityLevel.NORMAL)
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


    }
}
