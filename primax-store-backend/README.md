# primax-store-backend

An ecommerce store backend system built on top of Spring Boot project.


### Running the application

Build the Docker image by running the following command in the root directory of the project:

```bash
docker build -t primax-store-backend:latest .
```

Start a container using the following command:

```bash
docker run -d -p 8080:8080 primax-store-backend:latest
```

Spring Boot application by going to http://localhost:8080 in your web browser.


### Manually Running the application

Before to run this project, make sure postgres database is running on port `:5432`.

credentials should match with the project.

- username: `postgres`
- password: `password`
- And database name `primaxdb`

Now, run the project following goal:

```bash
./mvnw spring-boot:run
```

Then, the app run on port `:8080`

Enpoints and Methods are:

```
- /api/customers (GET)
- /api/customers/:id (GET)
- /api/customers/create (POST)
- /api/products (GET, POST)
- /api/products/:id (GET, PUT, DELETE)
- /api/products/search?name=product_name (GET)
- /api/products/sort_by?price="asc" [lowest] or "desc" [highest] (GET)
- /api/orders (GET)
- /api/orders/:id (GET)
- /api/orders/create (POST)
- /api/orders/approve/:id (PUT)
- /api/orders/reject/:id (DELETE)
```

Dependencies are

- spring-web
- spring jpa
- lombok
- devtools
- configuration-processor
- postgres
- spring-security (not implemented)

Backend Architecture
