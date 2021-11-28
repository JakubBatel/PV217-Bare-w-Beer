package cz.muni.fi.pv217.barewbeer;

import cz.muni.fi.pv217.barewbeer.entity.Customer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class CustomerTest {

//    @Test
//    public void createDeleteCustomer() {
//        Customer customer = new Customer("user1", "John", "Doe", "john@doe.com", null);
//
//        Object id = given()
//                .body(customer)
//                .contentType("application/json")
//                .when().post("/customers/create")
//                .then()
//                .statusCode(201)
//                .body("firstName", equalTo("John"))
//                .body("surname", equalTo("Doe"))
//                .extract().path("id");
//
//        given()
//                .when().get("customers/list")
//                .then()
//                .statusCode(200)
//                .body("size()",is(1))
//                .body("firstName", containsInAnyOrder("John"));
//
//        given()
//                .when().delete("/customers/" + id + "/delete")
//                .then()
//                .statusCode(200);
//
//        given()
//                .when().get("customers/list")
//                .then()
//                .statusCode(200)
//                .body("size()",is(0));
//    }
}
