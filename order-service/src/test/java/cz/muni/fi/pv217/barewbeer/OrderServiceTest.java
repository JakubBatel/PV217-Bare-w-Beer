package cz.muni.fi.pv217.barewbeer;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import com.radcortez.flyway.test.annotation.DataSource;
import com.radcortez.flyway.test.annotation.FlywayTest;

@QuarkusTest
@FlywayTest(@DataSource(QuarkusDataSourceProvider.class))
public class OrderServiceTest {
    @Test
    public void testCreateOrder() {
        given()
            .body("{\"confirmed\": false, \"shippedOut\": \"false\", \"items\": [{\"productId\": 1, \"pricePerUnit\": 12345.67, \"count\": 10}]}")
            .contentType(ContentType.JSON)
        .when()
            .post("/orders")
        .then()
            .statusCode(201)
            .body("confirmed", equalTo(false))
            .body("shippedOut", equalTo(false))
            .body("items.size()", is(1))
            .body("items[0].productId", equalTo(1))
            .body("items[0].pricePerUnit", equalTo(12345.67F))
            .body("items[0].count", equalTo(10));
    }

    @Test
    public void testGetOrder() {
        given()
        .when()
            .get("/orders/12345")
        .then()
            .statusCode(200)
            .body("id", equalTo(12345))
            .body("confirmed", equalTo(true))
            .body("shippedOut", equalTo(true))
            .body("items.id", hasItems(45678, 56789))
            .body("items.productId", hasItems(1, 2))
            .body("items.count", hasItems(1, 10));
    }

    @Test
    public void testGetOrders() {
        given()
        .when()
            .get("/orders")
        .then()
            .statusCode(200)
            .body("size()", is(3))
            .body("id", containsInAnyOrder(12345, 23456, 34567))
            .body("confirmed", containsInAnyOrder(true, true, false))
            .body("shippedOut", containsInAnyOrder(true, false, false));
    }

    @Test
    public void testGetNonExistingProduct() {
        given()
        .when()
            .get("/orders/1")
        .then()
            .statusCode(404);
    }
}
