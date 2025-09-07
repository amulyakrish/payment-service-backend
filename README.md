# payment-service-backend

A basic Spring Boot backend for payment processing with JWT security.

## Build & Run

```bash
mvn clean package
docker build -t payment-service-backend .
docker run -p 8080:8080 payment-service-backend
```