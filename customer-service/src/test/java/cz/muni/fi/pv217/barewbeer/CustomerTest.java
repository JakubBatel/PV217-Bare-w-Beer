package cz.muni.fi.pv217.barewbeer;

import com.radcortez.flyway.test.annotation.DataSource;
import com.radcortez.flyway.test.annotation.FlywayTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;


@QuarkusTest
@FlywayTest(@DataSource(QuarkusDataSourceProvider.class))
public class CustomerTest {

    @Test
    public void createCustomer() {
       given()
                .body("{\"firstName\": \"Jan\", \"surname\": \"Holy\", \"email\":\"jan@muni.cz\"}")
                .contentType(ContentType.JSON)
                .when().post("/customers")
                .then()
                .statusCode(201)
                .body("firstName", equalTo("Jan"))
                .body("surname", equalTo("Holy"))
                .body("email", equalTo("jan@muni.cz"));
    }

    @Test
    public void testListAll() {
        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
                .body("size()",is(3))
                .body("id", containsInAnyOrder(12345, 23456, 34567))
                .body("firstName", containsInAnyOrder("John", "Jason", "Jimmy"))
                .body("surname", containsInAnyOrder("Doe", "Blue", "Black"))
                .body("email", containsInAnyOrder("john@doe.com", "jason@blue.com", "jimmy@black.com"));
    }

    @Test
    public void testGetCustomer() {
        given()
                .when()
                .get("/customers/12345")
                .then()
                .statusCode(200)
                .body("id", equalTo(12345))
                .body("firstName", equalTo("John"))
                .body("surname", equalTo("Doe"))
                .body("email", equalTo("john@doe.com"));
    }
}
