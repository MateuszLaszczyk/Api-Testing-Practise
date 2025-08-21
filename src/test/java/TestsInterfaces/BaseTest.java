package TestsInterfaces;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;

public class BaseTest {

    protected static String trelloKey;
    protected static String trelloToken;
    protected static String boardId;

    @BeforeAll
    static void setup() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/java/properties/secrets.properties"));
        filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        filters(new AllureRestAssured());

        trelloKey = prop.getProperty("trello.key").trim();
        trelloToken = prop.getProperty("trello.token").trim();

        RestAssured.baseURI = "https://api.trello.com/1";

    }

    @BeforeEach
    void createBoard() throws IOException {
        boardId =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .queryParam("key", trelloKey)
                        .queryParam("token", trelloToken)
                        .queryParam("name", "Rest Assured Trello Board")
                        .log().body()
                        .when()
                        .post("/boards")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract()
                        .jsonPath()
                        .getString("id");

        System.out.println("Created boardId = " + boardId);
    }


    @AfterEach
    void cleanup() {
        if (BaseTest.boardId != null) {
            given()
                    .queryParam("key", BaseTest.trelloKey)
                    .queryParam("token", BaseTest.trelloToken)
                    .log().body()
                    .when()
                    .delete("/boards/{id}", BaseTest.boardId)
                    .then()
                    .statusCode(200)
                    .log().body();
            System.out.println("Deleted Board with id " + BaseTest.boardId);
        }
    }
}




