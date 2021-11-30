package cz.muni.fi.pv217.barewbeer;

import org.junit.jupiter.api.Test;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ProductServiceTest {
    @Test
    public void testCreateProduct() {
        given()
            .body("{\"name\": \"Test product\", \"description\": \"This is description\", \"price\": 12345.67, \"categories\": [\"PILSNER\", \"WITBIER\"]}")
            .contentType(ContentType.JSON)
        .when()
            .post("/products")
        .then()
            .statusCode(201)
            .body("name", equalTo("Test product"))
            .body("description", equalTo("This is description"))
            .body("price", equalTo(12345.67F))
            .body("categories", containsInAnyOrder("PILSNER","WITBIER"));
    }
}
