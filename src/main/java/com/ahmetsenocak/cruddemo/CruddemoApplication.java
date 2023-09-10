package com.ahmetsenocak.cruddemo;

import com.ahmetsenocak.cruddemo.dao.AppDAO;
import com.ahmetsenocak.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);
            //findStudentAndCourses(appDAO);
            //addMoreCoursesForStudent(appDAO);
            deleteStudent(appDAO);
        };
    }

    private void deleteStudent(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Deleting student " + theId);

        appDAO.deleteStudentById(theId);

        System.out.println("Done! check DB");
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int theId = 2;
        // find student
        Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

        // Create more Courses
        Course tempCourse1 = new Course("How to gain power in mmorpg games");
        Course tempCourse2 = new Course("How to earn money in mmorpg games");

        // Add courses to Student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Update Student " + tempStudent);
        System.out.println("Associated Courses " + tempStudent.getCourses());

        appDAO.update(tempStudent);

        System.out.println("Done! Check DB");

    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int theId = 1;
        Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

        System.out.println("Students " + tempStudent);
        System.out.println("Courses " + tempStudent.getCourses());
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int theId = 10;

        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);
        System.out.println("Loaded courses: " + tempCourse);
        System.out.println("Students " + tempCourse.getStudents());
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        // create course
        Course tempCourse = new Course("How to play mmorpg game crash course");

        // create student
        Student tempStudent1 = new Student("Ahmet", "Senocak", "ahmet@gmail.com");
        Student tempStudent2 = new Student("Ewa", "rock", "ewa.rock@gmail.com");

        // add students to course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save students
        System.out.println("Saving all students! ");
        appDAO.save(tempCourse);

        System.out.println("Done! check DB");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done! check DB");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
        // print the course
        System.out.println(tempCourse);
        // print the reviews
        System.out.println(tempCourse.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        // create a course
        Course tempCourse = new Course("Pacman - How to make 1k points");

        // add some reviews
        tempCourse.addReview(new Review("Great course loved it"));
        tempCourse.addReview(new Review("good tutorial"));
        tempCourse.addReview(new Review("Poor video quality"));

        // save the course
        System.out.println("Saving the Course...");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDAO.save(tempCourse);
    }

    private void deleteCourse(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Searching the id " + theId);
        appDAO.deleteCourseById(theId);

        System.out.println("Done! check DB");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 10;
        Course tempCourse = appDAO.findCourseById(theId);

        tempCourse.setTitle("TESTING_TWO");
        appDAO.update(tempCourse);

        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        // Find the Instructor
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        // Update the instructor
        tempInstructor.setLastName("TEST");
        appDAO.update(tempInstructor);

        System.out.println("Done! check DB");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Searching the id  " + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("Associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Searching the id " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("Result is " + tempInstructor);

        System.out.println("Finding courses for instructor id " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        // associate the courses
        tempInstructor.setCourses(courses);

        System.out.println("Associated courses: " + courses);
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Searching the id " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("The Instructor found: " + tempInstructor);

        System.out.println("Details: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // create the instructor
        Instructor tempInstructor =
                new Instructor("Ahmet", "Senocak", "ahmet@gmail.com");

        // create the instructor detail

        InstructorDetail tempInstructorDetail =
                new InstructorDetail("youtube.com", "Fun Videos");


        //  associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Course tempCourse1 = new Course("BigDawsTV");
        Course tempCourse2 = new Course("TheDailyDropOutTv");

        // Add course to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        // NOTE: this will also save the courses
        // because of the CascadeType.PERSIST
        appDAO.save(tempInstructor);
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 4;
        System.out.println("Searching the id: " + id);
        appDAO.deleteInstructorDetailById(id);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int id = 3;
        System.out.println("Searching the id " + id);
        InstructorDetail tempInstructor = appDAO.findInstructorDetailById(id);

        System.out.println("found " + tempInstructor);
        System.out.println("the associated instructorDetail is " + tempInstructor.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Searching the id: " + id);

        appDAO.deleteInstructorById(id);

    }

    private void findInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Searching the id: " + id);

        Instructor tempInstructor = appDAO.findInstructorById(id);

        System.out.println("Found " + tempInstructor);
        System.out.println("the associated instructorDetail is " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        // create the instructor
        Instructor tempInstructor =
                new Instructor("Ahmet", "Senocak", "ahmet@gmail.com");

        // create the instructor detail

        InstructorDetail tempInstructorDetail =
                new InstructorDetail("youtube.com", "Fun Videos");


        //  associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        // this will also save the detail because of (cascade = CascadeType.ALL)
        System.out.println("Saving instructor" + tempInstructor);
        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }
}
