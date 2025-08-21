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
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateAndAttachLabelToCardTest extends BaseTest {

    @Test
    @Epic("Trello API")
    @Feature("Label Management")
    @DisplayName("Create a label and attach it to a card")
    @Description("This test first creates a label on a board and then attaches it to a specific card.")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateLabelOnBoard() {
        // Create list
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

        // Create label on that list
        String labelId = given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("name", "First Label")
                .queryParam("color", "red")
                .queryParam("idBoard", boardId)
                .when()
                .post("/labels")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("id");

        assertNotNull(listId);
        assertNotNull(labelId);

        // Create card on that list
        String cardId =
                given()
                        .queryParam("key", trelloKey)
                        .queryParam("token", trelloToken)
                        .queryParam("idList", listId)
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .queryParam("name", "Card with label")
                        .when()
                        .post("/cards")
                        .then()
                        .statusCode(200)
                        .extract().path("id");

        // Attach label to card
        given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .queryParam("value", labelId)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post("/cards/{id}/idLabels", cardId)
                .then()
                .statusCode(200);

        // Assert that label exist
        given()
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when()
                .get("/cards/{id}", cardId)
                .then()
                .body("idLabels", hasItem(labelId));
    }


}

