package Student_Management_System;

import java.util.*;

public class Admin extends User {
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }


    public Course createCourse(String title, String description, int duration) {
        return new Course(title, description, duration);
    }

    public void addMaterial(Course course, String material) {
        course.addMaterial(material);
    }


    public void addQuestionToExam(Course course, Question question) {
        if (course.getExam() == null) {
            course.setExam(new Exam());
        }
        course.getExam().addQuestion(question);
    }

    public void deleteCourse(List<Course> courseList, Scanner sc) {
        if (courseList.isEmpty()) {
            System.out.println("No courses available to delete.");
            return;
        }

        System.out.println("\n--- Existing Courses ---");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println((i + 1) + ". " + courseList.get(i).getTitle());
        }

        System.out.print("Enter course number to delete: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > courseList.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        String removedTitle = courseList.get(choice - 1).getTitle();
        courseList.remove(choice - 1);
        System.out.println("Deleted course: " + removedTitle);
    }

    @Override
    public void showMenu() {
        System.out.println("Admin menu will be handled from Main based on context.");
    }
}

