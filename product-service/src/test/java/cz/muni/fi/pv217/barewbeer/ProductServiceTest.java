package cz.muni.fi.pv217.barewbeer;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import com.radcortez.flyway.test.annotation.DataSource;
import com.radcortez.flyway.test.annotation.FlywayTest;

@QuarkusTest
@FlywayTest(@DataSource(QuarkusDataSourceProvider.class))
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

    @Test
    public void testGetProduct() {
        given()
        .when()
            .get("/products/12345")
        .then()
            .statusCode(200)
            .body("id", equalTo(12345))
            .body("name", equalTo("Test product 1"))
            .body("description", equalTo("This is the first test product"))
            .body("price", equalTo(12345F))
            .body("categories", empty());
    }

    @Test
    public void testGetProducts() {
        given()
        .when()
            .get("/products")
        .then()
            .statusCode(200)
            .body("size()", is(3))
            .body("id", containsInAnyOrder(12345, 23456, 34567))
            .body("name", containsInAnyOrder("Test product 1", "Test product 2", "Test product 3"))
            .body("description", containsInAnyOrder("This is the first test product","This is the second test product", "This is the third test product"))
            .body("price", containsInAnyOrder(12345F, 0F, 0.99F));
    }

    @Test
    public void testGetNonExistingProduct() {
        given()
        .when()
            .get("/products/1")
        .then()
            .statusCode(404);
    }
}
