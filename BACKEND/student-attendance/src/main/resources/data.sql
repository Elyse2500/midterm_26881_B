
/*Insert sample Provinces*/
INSERT INTO provinces (code, name) VALUES ('KGL', 'Kigali');
INSERT INTO provinces (code, name) VALUES ('EST', 'Eastern');
INSERT INTO provinces (code, name) VALUES ('WST', 'Western');

/*Insert Users*/
INSERT INTO users (id, full_name, email, province_code)
VALUES (1, 'John Doe', 'john@example.com', 'KGL');

INSERT INTO users (id, full_name, email, province_code)
VALUES (2, 'Alice Smith', 'alice@example.com', 'EST');

INSERT INTO users (id, full_name, email, province_code)
VALUES (3, 'David Lee', 'david@example.com', 'KGL');

/*Insert Student Profiles (One-to-One with users)*/
INSERT INTO student_profiles (id, student_number, user_id)
VALUES (1, 'STU001', 1);

INSERT INTO student_profiles (id, student_number, user_id)
VALUES (2, 'STU002', 2);

INSERT INTO student_profiles (id, student_number, user_id)
VALUES (3, 'STU003', 3);


/*Insert Courses*/
INSERT INTO courses (id, code, title)
VALUES (1, 'CS101', 'Introduction to Programming');

INSERT INTO courses (id, code, title)
VALUES (2, 'CS202', 'Database Systems');

/*Insert Locations*/
INSERT INTO locations (id, campus_name, room, province_code)
VALUES (1, 'Main Campus', 'A101', 'KGL');

INSERT INTO locations (id, campus_name, room, province_code)
VALUES (2, 'East Campus', 'B202', 'EST');

/*Insert Enrollments (Many-to-Many)*/

INSERT INTO enrollments (id, student_id, course_id, enrolled_at)
VALUES (1, 1, 1, CURRENT_DATE);

INSERT INTO enrollments (id, student_id, course_id, enrolled_at)
VALUES (2, 2, 2, CURRENT_DATE);

INSERT INTO enrollments (id, student_id, course_id, enrolled_at)
VALUES (3, 3, 1, CURRENT_DATE);


/*Insert Attendance*/
INSERT INTO attendance (id, student_id, course_id, location_id, attended_on, status)
VALUES (1, 1, 1, 1, CURRENT_DATE, 'PRESENT');

INSERT INTO attendance (id, student_id, course_id, location_id, attended_on, status)
VALUES (2, 2, 2, 2, CURRENT_DATE, 'ABSENT');

