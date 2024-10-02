## Overview

This project is developed during an internship at the Laboratory of Software Engineering. It is an Exam Event Management System that allows candidates to create applications for exam events, view relevant information, and enables administrators to create exam events and manage applicants.

## Project Structure

.<br />
│<br />
├── backend/<br />
│   ├── src/<br />
│   │   ├── main/<br />
│   │   │   ├── java/<br />
│   │   │   │   └── fon/<br />
│   │   │   │           └── enrollmentsystembackend/<br />
│   │   │   │               ├── controller/<br />
│   │   │   │               ├── model/<br />
│   │   │   │               ├── repository/<br />
│   │   │   │               ├── ...<br />
│   │   │   │               └── service/<br />
│   │   │   └── resources/<br />
│   │   │       ├── application.properties<br />
│   │   │       └── db/<br />
│   │   └── test/<br />
│   └── pom.xml<br />
│<br />
└── frontend/<br />
    ├── src/<br />
    │   ├── app/<br />
    │   │   ├── pages/<br />
    │   │   ├── .../<br />
    │   │   └── app.module.ts<br />
    │   ├── public/assets/<br />
    │   └── index.html<br />
    └── ...<br />

## Functionalities

### Candidate Features
- Create applications for exam events.
- View personal applications.
- Access information about exam events.

### Admin Features
- Create and manage exam events.
- View applicants for specific exam events.

## Technologies Used

- **Backend:** Spring Boot, Hibernate ORM
- **Frontend:** Angular
- **Database:** MySQL

## Getting Started

### Prerequisites

Ensure you have the following installed on your system:

- Java 21 or higher
- Node.js and npm
- MySQL

### Installation

1. **Clone the repository**
2. **Set up the backend:**
> Set up the MySQL database using the SQL script provided in *backend/src/main/resources/db/set.sql*.<br />
> Configure the application.properties file with your database credentials.<br />
> Build and run the Spring Boot application:<br />
> cd backend
> ./mvnw spring-boot:run
3. **Set up the frontend:**
> Install the required packages:<br />
> cd frontend<br />
> npm install<br />
> Run the Angular application:<br />
> ng serve<br />
