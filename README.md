# Rest API - Prueba Técnica

## Descripción
API REST reactiva para gestionar franquicias → sucursales → productos.

Tecnologías: Spring Boot (WebFlux), Reactive MongoDB, Docker, Java 21.

## Requisitos
- Java 21
- Maven
- Docker & Docker Compose (opcional para levantar Mongo y la app)

## Endpoints principales
POST /api/franchises
- Crea una nueva franquicia.
- Body: { "name": "Franquicia X" }

POST /api/franchises/{franchiseId}/branches
- Agrega una sucursal.
- Body: { "name": "Sucursal A" }

POST /api/franchises/{franchiseId}/branches/{branchId}/products
- Agrega producto.
- Body: { "name":"Producto", "stock": 10 }

DELETE /api/franchises/{franchiseId}/branches/{branchId}/products/{productId}
- Elimina producto.

PATCH /api/franchises/{franchiseId}/branches/{branchId}/products/{productId}/stock
- Actualiza stock.
- Body: { "stock": 5 }

GET /api/franchises/{franchiseId}/top-products
- Retorna productos ordenados por stock (desc) incluyendo sucursal.

PUT /api/franchises/{franchiseId}/name
PUT /api/franchises/{franchiseId}/branches/{branchId}/name
PUT /api/franchises/{franchiseId}/branches/{branchId}/products/{productId}/name
- Endpoints extra para actualizar nombres.

## Ejecutar local (sin Docker)
1. Asegúrate que MongoDB esté corriendo en `localhost:27017`.
2. `mvn clean package`
3. `java -jar target/franchise-api-0.0.1-SNAPSHOT.jar`

## Ejecutar con Docker
1. `mvn clean package -DskipTests`
2. `docker-compose up --build`

## Tests
`mvn test`

## Notas Técnicas
- Arquitectura: Clean Architecture (domain, service, repository, controller).
- Programación reactiva (WebFlux): se usan `Mono` y `Flux`.
- Modelo: `Franchise` document con `branches` embebidas y `products`.
