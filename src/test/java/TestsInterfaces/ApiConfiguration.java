package TestsInterfaces;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class ApiConfiguration {
    protected static String trelloKey;
    protected static String trelloToken;

    @BeforeAll
     static void setup() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/java/properties/secrets.properties"));


        trelloKey = prop.getProperty("trello.key").trim();
        trelloToken = prop.getProperty("trello.token").trim();

        RestAssured.baseURI = "https://api.trello.com/1";

    }
}
