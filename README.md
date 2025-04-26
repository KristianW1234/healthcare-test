# Healthcare Product API

This is a API Framework for SatuSehat API. Created by Kristian.

## Features

- Basic CRUD feature of (Add, Update, Get, Delete)
- Multiple categories per product
- PostgreSQL-based database created automatically with JPA. Run the program and Hibernate will create the table for you!
- Image uploading (via moving to an Image directory)
- Environment-based configuration using `.env` + `application.properties`
- Basic Authorization system

## Tech Stack

- Java 17+
- Spring Boot
- Maven
- PostgreSQL
- JPA / Hibernate
- JSON file upload and image handling via `MultipartFile`

## Getting Started

### 1. Clone the Repo

```
Clone the terminal to your platform by using "git clone (copied clone address)"
```

### 2. Create ENV file

```
Create your own ENV file named '.env' in the same folder with pom.xml. Make sure it's filled with:

DB_URL=(Your PostgreSQL database url)
DB_USER=(Your PostgreSQL database username)
DB_PASSWORD=(Your PostgreSQL database password)
IMG_DIR=(Where you want to store the uploaded images)
EP_USER=(The username you will use to set the Basic Auth for API Request)
EP_PASSWORD=(The password you will use to set the Basic Auth for API Request)
```

### 3. Make sure everything on your end is ready, then run it once.

```
Make sure there is no error from your IDE side, and make sure you have installed PostgreSQL and the task is running.

Once the application runs once, the database schema will be created automatically.
```

### 4. Making API Request (Preferably Postman)

```
Ensure that you are using Basic Auth, and filling in the username and password as listed in your EP_USER and EP_PASSWORD

To create an API request about adding or updating Products, use form-data instead of raw (JSON)
```

### 5. When you shut down the application, modify application.properties

```
Comment this line:

$spring.jpa.hibernate.ddl-auto=create

Because if you don't, the next time you start up the application,
the database schema of healthcare will be reset
and you will have to start uploading data again from scratch!
```

## Note 
```
Full OAuth2 authentication via Auth0 is not implemented in this version.
Endpoints are currently protected using basic Spring Security with in-memory authentication.
This can be replaced with Auth0 integration when access to a valid JWT provider is available.
```

## Author
```
Kristian
Github: @KristianW1234
```

# License
```
This project is open-source and free to use.
```

