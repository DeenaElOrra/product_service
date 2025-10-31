package store.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
public class ProductResourceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/product";
    }

    @Test
    public void testCreateProduct_Success() {
        String requestBody = """
            {
                "name": "Arroz",
                "price": 5.99,
                "unit": "kg"
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", notNullValue())
            .body("name", equalTo("Arroz"))
            .body("price", equalTo(5.99f))
            .body("unit", equalTo("kg"));
    }

    @Test
    public void testCreateProduct_WithDifferentData() {
        String requestBody = """
            {
                "name": "Feijão",
                "price": 8.50,
                "unit": "kg"
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", notNullValue())
            .body("name", equalTo("Feijão"))
            .body("price", equalTo(8.50f))
            .body("unit", equalTo("kg"));
    }

    @Test
    public void testCreateProduct_WithDecimalPrice() {
        String requestBody = """
            {
                "name": "Óleo",
                "price": 12.75,
                "unit": "litro"
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post()
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", notNullValue())
            .body("name", equalTo("Óleo"))
            .body("price", equalTo(12.75f))
            .body("unit", equalTo("litro"));
    }
}
