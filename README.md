# Job Portal Project

This is a full-stack job portal project built with:

- Java 8+
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf, HTML5, CSS3, JavaScript, Bootstrap
- MySQL
- JPA / Hibernate
- Maven

## Features

- Candidate and recruiter registration
- Login and logout with Spring Security
- Recruiter dashboard to post jobs
- Candidate dashboard to track applications
- Public job listing and job details page
- Apply to jobs with resume link and cover letter
- Demo seed data for quick testing

## Project Structure

- `src/main/java/com/jobportal/config` - security and seed configuration
- `src/main/java/com/jobportal/controller` - MVC controllers
- `src/main/java/com/jobportal/model` - entities and enums
- `src/main/java/com/jobportal/repository` - JPA repositories
- `src/main/java/com/jobportal/service` - business logic
- `src/main/java/com/jobportal/web/dto` - form DTOs
- `src/main/resources/templates` - Thymeleaf frontend pages
- `src/main/resources/static` - CSS and JavaScript

## Database Setup

### Local MySQL

1. Install MySQL Server and make sure it is running.
2. Create a database named `jobportal_db`, or let Spring create it from the configured URL.
3. Set these environment variables if your local credentials are different:
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
4. Make sure MySQL is running on port `3306`.

### Cloud MySQL

For Aiven or another hosted MySQL database, set:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Example JDBC URL:

```text
jdbc:mysql://your-host:your-port/defaultdb?sslMode=REQUIRED&serverTimezone=Asia/Kolkata
```

## Run the Project

```bash
mvn spring-boot:run
```

Open `http://localhost:8080`

## Demo Accounts

- Recruiter: `recruiter@jobportal.com` / `Recruiter@123`
- Candidate: `candidate@jobportal.com` / `Candidate@123`

## GitHub Safety

Do not commit real database passwords into `application.properties`.
Use environment variables instead.

## Ideas for Next Improvements

- Add admin dashboard
- Upload resumes as files instead of links
- Add search and filters
- Add recruiter ability to update application status
- Add REST APIs for Postman testing
