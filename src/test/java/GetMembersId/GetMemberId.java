package GetMembersId;

import BaseTest.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetMemberId extends BaseTest {

    @Test
    public void getMemberId() {
        String memberID = given()
        .queryParam("token", trelloToken)
                .queryParam("key", trelloKey)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/members/{id}", userName)
                .then()
                .statusCode(200)
                .extract().body().path("id");

        System.out.println("Member id is:  " + memberID);
    }
}
