# Student Attendance System

A REST API built with Spring Boot that manages student attendance records across multiple courses and campus locations.

**Developer:** Igihozo Elyse
**University:** Adventist University of Central Africa (AUCA)  
**Course:** Web Technology and Internet  

---

## What This Project Does

This system allows a university to:
- Manage users (students) and assign them to provinces
- Create and manage courses
- Track campus locations across provinces
- Enroll students into courses
- Record and monitor student attendance (PRESENT, ABSENT, LATE)

---

## Tech Stack

- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Swagger (SpringDoc OpenAPI)**
- **Maven**

---

## Database Structure (ERD)

7 tables with the following relationships:

```
Province (code, name)
    |
    |-- One-to-Many --> AppUser (id, fullName, email, province_code)
    |                       |
    |                       |-- One-to-One --> StudentProfile (id, studentNumber, user_id)
    |                                               |
    |                                               |-- Many-to-Many --> Course (id, code, title)
    |                                               |     via Enrollment (id, student_id, course_id)
    |                                               |
    |                                               |-- One-to-Many --> Attendance (id, student_id, course_id, location_id, date, status)
    |
    |-- One-to-Many --> Location (id, campusName, room, province_code)
```

### Relationships:
- **One-to-Many:** Province → AppUser, Province → Location
- **One-to-One:** AppUser ↔ StudentProfile
- **Many-to-Many:** StudentProfile ↔ Course (via Enrollment join table)

---

## Getting Started

### Prerequisites
- Java 17+
- PostgreSQL
- Maven

### Database Setup
Create a PostgreSQL database:
```sql
CREATE DATABASE attendance_db;
```

### Configuration
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/attendance_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run the Application
```bash
./mvnw spring-boot:run
```

The application starts at `http://localhost:8080`  
Swagger UI is available at `http://localhost:8080/swagger-ui`

---

## API Endpoints

### Provinces
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/provinces` | Create a province |
| GET | `/api/provinces` | List all provinces (paginated) |
| GET | `/api/provinces/{code}` | Get province by code |
| PUT | `/api/provinces/{code}` | Update province |
| DELETE | `/api/provinces/{code}` | Delete province |

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users` | Create a user |
| GET | `/api/users` | List all users (paginated, sorted by fullName) |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/by-province?value=KGL` | Get users by province code or name |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

### Locations
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/locations` | Create a location |
| GET | `/api/locations` | List all locations (paginated, sorted by campusName) |
| GET | `/api/locations/{id}` | Get location by ID |
| PUT | `/api/locations/{id}` | Update location |
| DELETE | `/api/locations/{id}` | Delete location |

### Courses
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/courses` | Create a course |
| GET | `/api/courses` | List all courses (paginated, sorted by title) |
| GET | `/api/courses/{id}` | Get course by ID |
| PUT | `/api/courses/{id}` | Update course |
| DELETE | `/api/courses/{id}` | Delete course |

### Students
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/students` | Create a student profile |
| GET | `/api/students` | List all students (paginated) |
| GET | `/api/students/{id}` | Get student by ID |
| PUT | `/api/students/{id}` | Update student profile |
| DELETE | `/api/students/{id}` | Delete student profile |

### Enrollments
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/enrollments` | Enroll student in a course |
| GET | `/api/enrollments` | List all enrollments (paginated) |
| GET | `/api/enrollments/{id}` | Get enrollment by ID |
| DELETE | `/api/enrollments/{id}` | Delete enrollment |

### Attendance
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/attendance` | Record attendance |
| GET | `/api/attendance` | List all attendance (paginated, sorted by date) |
| GET | `/api/attendance/{id}` | Get attendance by ID |
| PUT | `/api/attendance/{id}` | Update attendance |
| DELETE | `/api/attendance/{id}` | Delete attendance |

---

## Sample Postman Requests

### 1. Create Province
```json
POST /api/provinces
{
  "code": "KGL",
  "name": "Kigali"
}
```

### 2. Create User
```json
POST /api/users
{
  "fullName": "Patrick DUSHIMIMANA",
  "email": "patrick@auca.ac.rw",
  "provinceCode": "KGL"
}
```

### 3. Create Location
```json
POST /api/locations
{
  "campusName": "Main Campus",
  "room": "A101",
  "provinceCode": "KGL"
}
```

### 4. Create Course
```json
POST /api/courses
{
  "code": "CS101",
  "title": "Introduction to Programming"
}
```

### 5. Create Student Profile
```json
POST /api/students
{
  "studentNumber": "STU001",
  "userId": 1
}
```

### 6. Enroll Student in Course
```json
POST /api/enrollments
{
  "studentId": 1,
  "courseId": 1
}
```

### 7. Record Attendance
```json
POST /api/attendance
{
  "studentId": 1,
  "courseId": 1,
  "locationId": 1,
  "attendedOn": "2026-03-13",
  "status": "PRESENT"
}
```

### 8. Get Users by Province
```
GET /api/users/by-province?value=KGL
GET /api/users/by-province?value=Kigali
```

### 9. Pagination & Sorting
```
GET /api/users?page=0&size=5&sort=fullName,asc
GET /api/locations?page=0&size=10&sort=campusName,desc
```

---

## Key Implementations

### Pagination & Sorting
```java
@GetMapping
public Page<AppUser> list(
    @PageableDefault(size = 10, sort = "fullName") Pageable pageable) {
  return userRepo.findAll(pageable);
}
```

### existBy() Method
```java
boolean existsByEmail(String email); // in AppUserRepository
```

### Province Query (code OR name)
```java
Page<AppUser> users = userRepo.findByProvince_CodeIgnoreCase(value, pageable);
if (users.isEmpty()) {
  users = userRepo.findByProvince_NameIgnoreCase(value, pageable);
}
```

---

## Sample Data
The application loads sample data on startup from `data.sql`:
- 3 Provinces: Kigali, Eastern, Western
- 3 Users with student profiles
- 2 Courses
- 2 Locations
- 3 Enrollments
- 2 Attendance records
