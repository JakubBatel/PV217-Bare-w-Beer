package cz.muni.fi.pv217.barewbeer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cz.muni.fi.pv217.barewbeer.client.Order;
import cz.muni.fi.pv217.barewbeer.client.OrderServiceClient;
import cz.muni.fi.pv217.barewbeer.client.Product;
import cz.muni.fi.pv217.barewbeer.client.ProductServiceClient;
import cz.muni.fi.pv217.barewbeer.service.ShoppingCartService;
import cz.muni.fi.pv217.barewbeer.utils.ShoppingCart;
import cz.muni.fi.pv217.barewbeer.utils.ShoppingCartItem;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;

@QuarkusTest
public class ShoppingCartTest {
    
    @Inject
    ShoppingCartService shoppingCartService;

    @InjectMock
    @RestClient
    ProductServiceClient productServiceClient;

    @InjectMock
    @RestClient
    OrderServiceClient orderServiceClient;

    @BeforeEach
    public void beforeEach() {
        shoppingCartService.clearShoppingCart(1L);
        shoppingCartService.clearShoppingCart(2L);
    }

    @Test
    public void testAddItemToCartEndpointSameCustomer() {
        // Arrange
        ShoppingCart expectedCart = new ShoppingCart();
        expectedCart.addItem(getTestCartItem1());
        expectedCart.addItem(getTestCartItem2());

        Map<Long, ShoppingCart> expectedShoppingCarts = new HashMap<Long, ShoppingCart>();
        expectedShoppingCarts.put(1L, expectedCart);
        
        Mockito
            .doReturn(Response.ok(getTestProduct1()).build())
            .when(productServiceClient)
            .getProduct(1);
    
        Mockito
            .doReturn(Response.ok(getTestProduct2()).build())
            .when(productServiceClient)
            .getProduct(2);
        
        // Act & Assert
        given()
            .body("{\"productId\": 1, \"count\": 10}")
            .contentType(ContentType.JSON)
        .when()
            .put("cart/1/add")
        .then()
            .statusCode(200);

        given()
            .body("{\"productId\": 2, \"count\": 20}")
            .contentType(ContentType.JSON)
        .when()
            .put("cart/1/add")
        .then()
            .statusCode(200);

        Assertions.assertEquals(expectedShoppingCarts, shoppingCartService.getAllShoppingCarts());
    }

    @Test
    public void testAddItemToCartEndpointDifferentCustomer() {
        // Arrange
        ShoppingCart expectedCart1 = new ShoppingCart();
        ShoppingCart expectedCart2 = new ShoppingCart();
        expectedCart1.addItem(getTestCartItem1());
        expectedCart2.addItem(getTestCartItem2());

        Map<Long, ShoppingCart> expectedShoppingCarts = new HashMap<Long, ShoppingCart>();
        expectedShoppingCarts.put(1L, expectedCart1);
        expectedShoppingCarts.put(2L, expectedCart2);
        
        Mockito
            .doReturn(Response.ok(getTestProduct1()).build())
            .when(productServiceClient)
            .getProduct(1);
    
        Mockito
            .doReturn(Response.ok(getTestProduct2()).build())
            .when(productServiceClient)
            .getProduct(2);

        // Act & Assert
        given()
            .body("{\"productId\": 1, \"count\": 10}")
            .contentType(ContentType.JSON)
        .when()
            .put("cart/1/add")
        .then()
            .statusCode(200);

        given()
            .body("{\"productId\": 2, \"count\": 20}")
            .contentType(ContentType.JSON)
        .when()
            .put("cart/2/add")
        .then()
            .statusCode(200);

        Assertions.assertEquals(expectedShoppingCarts, shoppingCartService.getAllShoppingCarts());
    }

    @Test
    public void testRemoveItemFromCart() {
        // Arrange
        Map<Long, ShoppingCart> expectedShoppingCarts = new HashMap<Long, ShoppingCart>();
        shoppingCartService.addItemToShoppingCart(1, getTestCartItem1());
        
        // Act & Assert
        given()
            .body("1")
            .contentType(ContentType.JSON)
        .when()
            .put("cart/1/remove")
        .then()
            .statusCode(200);

        Assertions.assertEquals(expectedShoppingCarts, shoppingCartService.getAllShoppingCarts());
    }

    @Test 
    public void testUpdateItemCountInCart() {
        // Arrange
        int expectedCount = 1000;
        ShoppingCart expectedCart = new ShoppingCart();
        expectedCart.addItem(getTestCartItem1());
        expectedCart.getItems().get(0).setCount(expectedCount);
        
        Map<Long, ShoppingCart> expectedShoppingCarts = new HashMap<Long, ShoppingCart>();
        expectedShoppingCarts.put(1L, expectedCart);

        shoppingCartService.addItemToShoppingCart(1, getTestCartItem1());

            // Act & Assert
        given()
            .body("{\"productId\": 1, \"count\": 1000}")
            .contentType(ContentType.JSON)
        .when()
            .put("cart/1/count")
        .then()
            .statusCode(200);

        Assertions.assertEquals(expectedShoppingCarts, shoppingCartService.getAllShoppingCarts());
    }

    @Test
    public void testClearShoppingCart() {
        // Arrange
        Map<Long, ShoppingCart> expectedShoppingCarts = new HashMap<Long, ShoppingCart>();

        shoppingCartService.addItemToShoppingCart(1, getTestCartItem1());
        
        // Act & Assert
        given()
        .when()
            .put("cart/1/clear")
        .then()
            .statusCode(200);

        Assertions.assertEquals(expectedShoppingCarts, shoppingCartService.getAllShoppingCarts());
    }

    @Test
    public void testOrderShoppingCart() {
        // Arrange
        Map<Long, ShoppingCart> expectedShoppingCarts = new HashMap<Long, ShoppingCart>();

        shoppingCartService.addItemToShoppingCart(1, getTestCartItem1());

        Mockito
            .doReturn(null)
            .when(orderServiceClient)
            .createOrder(new Order());
        
        // Act & Assert
        given()
        .when()
            .put("cart/1/order")
        .then()
            .statusCode(200)
            .body("items.size()", is(1))
            .body("items[0].productId", is(1))
            .body("items[0].pricePerUnit", is(1000))
            .body("items[0].count", is(10))
            .body("creationTimestamp", is(notNullValue()))
            .body("confirmed", is(false))
            .body("shippedOut", is(false));
        
        Assertions.assertEquals(expectedShoppingCarts, shoppingCartService.getAllShoppingCarts());
    }

    @Test
    public void testGetShoppingCart() {
        // Arrange
        shoppingCartService.addItemToShoppingCart(1, getTestCartItem1());
        
        // Act & Assert
        given()
        .when()
            .get("cart/1")
        .then()
            .statusCode(200)
            .body("items.size()", is(1))
            .body("items[0].productId", is(1))
            .body("items[0].name", is("Test Product 1"))
            .body("items[0].description", is("First testing product"))
            .body("items[0].price", is(1000))
            .body("items[0].count", is(10));
    }

    private ShoppingCartItem getTestCartItem1() {
        return new ShoppingCartItem(1L, "Test Product 1", "First testing product", new BigDecimal(1000), 10);
    }

    private ShoppingCartItem getTestCartItem2() {
        return new ShoppingCartItem(2L, "Test Product 2", "Second testing product", new BigDecimal(2000), 20);
    }

    private Product getTestProduct1() {
        return new Product(1L, "Test Product 1", "First testing product", new BigDecimal(1000));
    }

    private Product getTestProduct2() {
        return new Product(2L, "Test Product 2", "Second testing product", new BigDecimal(2000));
    }
}
