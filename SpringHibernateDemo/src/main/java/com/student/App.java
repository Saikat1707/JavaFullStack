package com.student;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        // 1. Start Spring container
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Get StudentService from Spring
        StudentService service =
                context.getBean(StudentService.class);


        // =========================
        // CREATE
        // =========================

        Student student = new Student(
                "Saikat",
                "saikat@gmail.com",
                "Btech"
        );

        service.save(student);

        System.out.println("Student saved successfully");


        // =========================
        // READ ONE
        // =========================

        Student result = service.findById(1);

        System.out.println("Student found:");
        System.out.println(result);


        // =========================
        // READ ALL
        // =========================

        List<Student> students = service.getAll();

        System.out.println("All students:");

        for (Student s : students) {
            System.out.println(s);
        }


        // =========================
        // UPDATE
        // =========================

        Student updatedStudent = new Student(
                "Saikat Bera",
                "saikat.bera@gmail.com",
                "MCA"
        );

        service.update(updatedStudent);

        System.out.println("Student updated successfully");


        // =========================
        // DELETE
        // =========================

        service.delete(1);

        System.out.println("Student deleted successfully");


        // Close Spring container
        context.close();
    }
}