# Newsletter Service
Spring Boot application that schedules newsletter content per topic and emails it to subscribed users at the specified time.

## Features 
- Manage topics (create/list). 
- Manage subscribers and their topic subscriptions.
- Create content with scheduledAt timestamps—one topic per content.
- Quartz scheduler (JDBC store) dispatches content via email at the scheduled time.
- Send logs record per-subscriber delivery status and errors.

## Tech Stack
- Spring Boot 3.5
- PostgreSQL 16
- Quartz with JDBC job store
- JavaMailSender (local dev via MailCatcherP)
- Docker/Docker Compose for local Postgres + MailCatcher
- Maven wrapper for builds

Local Setup
------------
Start infrastructure
 - docker-compose up -d

Set environment variables
-    DATABASE_URL=jdbc:postgresql://localhost:5432/postgres
-    DB_USER=postgres
-    DB_PASS=password
-    SMTP_HOST=localhost
-    SMTP_PORT=1025
-    SMTP_USER=
-    SMTP_PASS=
-    MAIL_FROM=newsletter@example.com

Run maven
- ./mvnw spring-boot:run

Test API's
- Test API's given in postman export file attached in the repo.

Build and run dockerfile.
 
