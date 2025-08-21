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

public class InivteMemberToBoardViaEmailTest extends BaseTest {


    @Test
    @Epic("Trello API")
    @Feature("Member Management")
    @DisplayName("Invite a member to board via email")
    @Description("Verifies that a user can invite another member to a Trello board using their email address.")
    @Severity(SeverityLevel.CRITICAL)
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
