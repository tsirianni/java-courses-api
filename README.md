# java-courses-api

This repo contains my solution for the 2nd challenge on the Rocketseat's Java Path.

## Highlights

Here are some of the things I've done that could be considered highlights to the project.

### Docker compose for local development

I have implemented a simple docker compose file to launch a postgresql container, with its
configuration in a .env file. There is a .env.sample file versioned that could be used as template
to set the necessary configuration.

### Manage Migrations with Flyway

From the start I wasn't a big fan of managing the database structure via changes in the Entity
class, so I have opted-out of this by setting `pring.jpa.hibernate.ddl-auto=none`and I have added
Flyway as a dependency to handle DB migrations, which offers not only control over the DB structure
as well as a more friendly approach to check DB structure history.

### Application.properties.sample

THe application.properties file is not versioned (and I am not sure how this works on real
production-ready projects), but I have opted to leave a sample and have the file created as needed.

### DTOs

I have decided to set a DTO for each route and leave the Jakarta validations on the DTOs instead of
mixing the validation with the entity definition. This (at least in my view) offers a cleaner
approach and better organizes the code.

### Unprocessable Entity Errors

There are a few instances of an Unprocessable Entity, their structure includes an error message and
an error type. The error type could be used by the front-end to customize feedback to end users.

### Utility class

There is a class `Utils` which I have though I could be useful to store reusable code, such as a
regex pattern for UUIDs. Other things could be added there as well, and since it is only an utility
class there is no need for Spring to manage it.
