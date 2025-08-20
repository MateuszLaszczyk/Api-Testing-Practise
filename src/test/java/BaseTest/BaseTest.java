package BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected static String trelloKey;
    protected static String trelloToken;
    protected static String boardId;

    @BeforeAll
    static void setup() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/java/properties/secrets.properties"));


        trelloKey = prop.getProperty("trello.key").trim();
        trelloToken = prop.getProperty("trello.token").trim();

        RestAssured.baseURI = "https://api.trello.com/1";

    }

//    @AfterEach
//    void cleanup(){
//        if (boardId != null){
//            given()
//                    .baseUri("https://api.trello.com/1")
//                    .queryParam("key", trelloKey)
//                    .queryParam("token", trelloToken)
//                    .log().all()
//                    .when()
//                    .delete("/boards/{id}", boardId)
//                    .then()
//                    .statusCode(200)
//                    .log().all();
//            System.out.println("Deleted Board with id " + boardId);
//        }
//    }

    }

