# Assignment 4 – Advanced OOP & SOLID Architecture (Java + JDBC)

## Project Overview
This project was developed as part of **Assignment 4**, which is an extended and improved version of Assignment 3.
The goal of this assignment is to apply **SOLID principles**, **advanced Object-Oriented Programming concepts**,
and modern Java features in a layered application that interacts with a relational database using **JDBC**.

The application represents a **Student Course Registration System** that allows managing students, courses,
and enrolling students into courses.

The project follows a clean multi-layer architecture:
controller → service → repository → database.

---

## Application Architecture

The application is structured into the following layers:

- **Controller layer**
  - Handles user actions
  - Calls service methods
  - Prints results to the console

- **Service layer**
  - Contains business logic and validation
  - Applies SOLID principles
  - Handles application-level exceptions
  - Does not work directly with JDBC

- **Repository layer**
  - Handles database access using JDBC
  - Contains SQL queries
  - Implements generic CRUD operations
  - Converts SQLExceptions into RuntimeExceptions

- **Model layer**
  - Contains domain entities

- **Utils**
  - Database connection utilities
  - Reflection utilities

---

## Entities and Relationships

### Entities
- **Student** (id, name, email)
- **Course** (id, name, credits)
- **Enrollment** (student_id, course_id)

### Relationships
- One student can enroll in many courses
- One course can contain many students
- The many-to-many relationship is implemented using the `enrollments` table

---

## Database Description

### DBMS
PostgreSQL

### Database Schema

```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    credits INT CHECK (credits > 0)
);

CREATE TABLE enrollments (
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);
OOP Concepts Used
Abstraction

BaseEntity is an abstract class that contains common fields and abstract methods such as getInfo() and isValid().

Inheritance

Student extends BaseEntity

Course extends BaseEntity

Polymorphism

Objects of Student and Course are accessed through BaseEntity references,
and overridden methods are resolved at runtime.

Encapsulation

All entity fields are private and accessed through public methods.

Composition

The Enrollment class demonstrates composition by holding references to Student and Course.

Interfaces and Generics

Printable – defines object printing behavior

Validatable – defines validation logic

CrudRepository<T> – generic CRUD operations interface

Repositories (StudentRepository, CourseRepository, EnrollmentRepository)
implement CrudRepository<T> to ensure consistency and code reuse.

Exception Handling

The application uses custom exceptions:

InvalidInputException

DuplicateResourceException

ResourceNotFoundException

DatabaseOperationException

Exception Strategy

Repository layer catches SQLException and throws RuntimeException

Service layer converts them into domain-specific exceptions

Controller layer does not handle database exceptions directly

Advanced Java Features
Lambda Expressions

Lambda expressions are used for sorting and processing collections:

list.sort((a, b) -> a.getName().compareTo(b.getName()));

Reflection

Reflection is used to inspect runtime class information:

ReflectionUtils.printClassInfo(student);

How to Run

Create a PostgreSQL database

Execute the SQL schema

Configure database credentials in DatabaseConnection

Run Main.java

Example Output
Student: Ali | email: a@mail.com
Student: Arkat | email: arkat@mail.com
Student: Beka | email: b@mail.com
Reflection: class name = model.Student
Demo finished OK

Conclusion

This project fully satisfies all requirements of Assignment 4 and demonstrates:

SOLID principles

Layered architecture

Advanced OOP concepts

Generics

Lambda expressions

Reflection

JDBC database interaction

Robust exception handling

Author

Student: Arkat Toktar
Course: Object-Oriented Programming
Assignment: 4
Language: Java
