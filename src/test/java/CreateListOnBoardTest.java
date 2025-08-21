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

public class CreateListOnBoardTest extends BaseTest {

    @Test
    @Epic("Trello API")
    @Feature("List Management")
    @DisplayName("Create a new list on the board")
    @Description("This test verifies that a user can successfully create a new list on a Trello board using the boardId.")
    @Severity(SeverityLevel.CRITICAL)
    public void createListOnBoardTest() {
        String listId = given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("name", "To Do")
                .when()
                .post("/boards/{id}/lists", boardId)
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("id");
    }
}
