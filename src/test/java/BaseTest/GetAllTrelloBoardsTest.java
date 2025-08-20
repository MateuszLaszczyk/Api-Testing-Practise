package BaseTest;


import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class GetAllTrelloBoardsTest extends BaseTest {


    @Test
    public void getAllTrelloBoards() {

        given()
                .baseUri("https://api.trello.com/1")
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when()
                .get("/members/me/boards")
                .then()
                .statusCode(200)
                .log().body();

    }
}
