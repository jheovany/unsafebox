# UnsafeBox

UnsafeBox is a REST API application developed with Spring Boot and Kotlin that allows users to create virtual "safe boxes" to securely store information.

## Main Features

- Creation of safe boxes with name and password
- Storage of items in safe boxes
- Retrieval of items stored in safe boxes
- RESTful API documented with OpenAPI/Swagger

## Technologies Used

- Kotlin 1.9.25
- Spring Boot 3.5.4
- Spring Data JPA
- PostgreSQL
- Gradle
- OpenAPI/Swagger for API documentation

## Prerequisites

- JDK 17 or higher
- PostgreSQL
- Gradle (included as wrapper)

## Database Configuration

The application uses PostgreSQL as a database. The script to create the database is located in `db/unsafebox.sql`.

## How to Run the Application

1. Clone the repository
2. Configure the PostgreSQL database using the provided script
3. Configure the database connection properties in `src/main/resources/application.properties`
4. Run the application with the command:

```bash
./gradlew bootRun
```

## API Endpoints

- `POST /safeboxes`: Creates a new safe box
- `GET /safeboxes/{id}/items`: Retrieves the content of a safe box
- `PUT /safeboxes/{id}/items`: Adds items to a safe box

For more details, check the API documentation accessible through Swagger UI at `/swagger-ui.html` when the application is running.

## Project Structure

- `src/main/kotlin/com/securityish/unsafebox/`: Application source code
  - `Application.kt`: Application entry point
  - `Entities.kt`: Database entities
  - `Item.kt`: Data model for items
  - `Repositories.kt`: Repositories for data access
  - `SafeBoxController.kt`: REST controller
  - `SafeBoxService.kt`: Business logic
  - `SafeBoxData.kt`: Data transfer classes
  - `OpenApiConfig.kt`: OpenAPI configuration

## License

This project is licensed under the terms of the MIT license.