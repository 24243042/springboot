# Registration API

Spring Boot REST API that validates registration email addresses and minimum age.

## Run

Requires Java 21 or later and Maven.

```powershell
mvn spring-boot:run
```

The default minimum age is 18. Set `REGISTRATION_MINIMUM_AGE` to 18 or greater to change it. The application fails to start if the configured value is below 18.

## Register

`POST /api/registrations`

```json
{
  "email": "person@example.com",
  "age": 18
}
```

Successful registrations return `201 Created`. Invalid email/age and ages below the configured minimum return `400 Bad Request`.

Run tests with `mvn test`.

## Run with Docker

```powershell
docker build -t registration-api .
docker run --rm -p 8080:8080 -e REGISTRATION_MINIMUM_AGE=21 registration-api
```

Omit `-e REGISTRATION_MINIMUM_AGE=21` to use the default minimum age of 18.