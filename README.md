# assignment3 – Advanced OOP API Project (JDBC + Exception Handling)

## Project Overview
This project is a console-based Java application developed as part of Assignment 3.
The goal of the project is to demonstrate Object-Oriented Programming (OOP)
principles together with JDBC and a relational database (PostgreSQL).

The application represents a simple Student Course Registration system.
It allows managing students and courses, as well as enrolling students into courses.

The project follows a multi-layer architecture:
controller → service → repository.

---

## Entities and Relationships

### Entities
- Student (id, name, email)
- Course (id, name, credits)
- Enrollment (student_id, course_id)

### Relationships
- One student can enroll in many courses
- One course can contain many students

This many-to-many relationship is implemented using the `enrollments` table
with foreign key constraints.

---

## OOP Design

### Abstract Class
BaseEntity is an abstract class that contains common fields (`id`, `name`)
and abstract methods (`getInfo()`, `isValid()`).
It demonstrates abstraction and encapsulation.

### Inheritance
- Student extends BaseEntity
- Course extends BaseEntity

### Polymorphism
Student and Course objects are accessed through BaseEntity references.
Overridden methods are called dynamically at runtime.

### Composition
Enrollment demonstrates composition by containing references
to Student and Course objects.

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
