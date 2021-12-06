# PV217 Bare w/ Beer

Bare with beer is a beer eshop. Example use scenario can be the following
1. Some manager add list of the products to the system.
2. New customer is created in the system.
3. The customer lists the available products.
4. The customer adds some items to the shopping cart.
5. The customer creates an order.
6. The manager accepts the order.

Microservices are propriate for this use case because they provide better scaling options than the monolith application. For example the product service might be overloaded sooner than order service because a lot of people will browse the products but just few of them will actually buy something.
On the other hand microservices brings additional complexity to the project and small eshop like this one should work without any trouble even when the monolith solution is used.

## Swagger
- Customer service
    - Open API scheme: http://localhost:8080/swagger/
    - Swagger UI: http://localhost:8080/q/swagger-ui/
- Order service
    - Open API scheme: http://localhost:8081/swagger/
    - Swagger UI: http://localhost:8081/q/swagger-ui/
- Product service
    - Open API scheme: http://localhost:8082/swagger/
    - Swagger UI: http://localhost:8082/q/swagger-ui/
- Shopping cart service
    - Open API scheme: http://localhost:8083/swagger/
    - Swagger UI: http://localhost:8083/q/swagger-ui/

## Health Check
- Liveness service/DB health check:
    - Customer service: http://localhost:8080/q/health
    - Order service: http://localhost:8081/q/health
    - Product service: http://localhost:8082/q/health
    - Shopping cart service: http://localhost:8083/q/health
