# PV217 Bare w/ Beer

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