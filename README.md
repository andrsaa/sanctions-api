# SANCTIONS API
Uses PostgreSQL extension pg_trgm to perform trigram matching on names of sanctioned individuals and entities.

## Deployment
### Dependencies
* Gradle
* Docker compose

### Download source code
```bash
git clone https://github.com/andrsaa/sanctions-api.git
```

### Create database
Build docker image and deploy. Exposes port 7900.
```bash
docker-compose up postgres -d
```

Run database migrations with Liquibase.
```bash
./gradlew update
```

### Building and deployment
Build war file.
```bash
./gradlew clean war
```

Build docker image and deploy. Exposes port 8080.
```bash
docker compose up app -d
```

## OpenAPI
OpenAPI documentation is available [here](http://localhost:8080/sanctions-api/swagger-ui/index.html) once the application is running.

## Authorization
This sample project uses Basic access authentication. The credentials are **user** and **password**. Add the following header 
to your requests to authenticate.
```http request
Authorization: Basic dXNlcjpwYXNzd29yZA==
```

## Tests
### Unit tests
```bash
./gradlew testUnit
```

### Integration tests
Uses testcontainers to run a PostgreSQL container in Docker and execute tests against it.
```bash
./gradlew testIntegration
```