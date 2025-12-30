# Corpevent – Full Local Setup Guide

Corpevent is a full-stack web application for managing corporate events and access requests.

This guide explains **how to fully run the application locally**, including:
- database
- backend
- frontend

---

## Requirements

You must have installed:

- Git
- Docker
- Docker Compose
- Node.js (v18+ recommended)
- npm
- Java JDK 17+
- Maven
- Angular CLI

---

## 1. Clone the repository

```bash
git clone https://github.com/VornicuAndreiPetru/corpevent.git
cd corpevent
```

## 2. Environment variables
Create a .env file in the backend root directory:

````
# === DATABASE ===
DB_URL=put_db_url
DB_USERNAME=db_username
DB_PASSWORD=db_pass

# === ORACLE CONTAINER ===
ORACLE_PASSWORD=put_oracle_pass
APP_USER=your_app_user
APP_USER_PASSWORD=your_app_pass

# === MAIL ===
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_app_password

# === JWT ===
JWT_SECRET_KEY=change_this_to_a_long_secret_key_32+chars
JWT_EXPIRATION=8640000
````
⚠️ For Gmail, you must use an App Password, not your normal password.

## 3. Start backend & database (Docker)
From the **project root**:
````
docker compose up -d
mvn spring-boot:run
````
This will start:
- Oracle database
- Spring Boot backend

## 4. Frontend (Angular)
````
ng serve
````

## 5. Default admin account
On first startup, an admin user is automatically created:
````
Username: admin
Password: admin
````