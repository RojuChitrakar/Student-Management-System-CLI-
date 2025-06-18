package Student_Management_System;


import java.util.*;

public class Student extends User {
    private List<Course> enrolledCourses = new ArrayList<>();
    private Map<Course, Result> courseResults = new HashMap<>();

    public Student(String name, String email, String password) {
        super(name, email, password);
    }

    public void registerCourse(List<Course> allCourses, Scanner sc) {
        System.out.println("\nAvailable Courses:");
        for (int i = 0; i < allCourses.size(); i++) {
            System.out.println((i + 1) + ". " + allCourses.get(i).getTitle());
        }

        System.out.print("Enter course number to register: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > allCourses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course selectedCourse = allCourses.get(choice - 1);

        if (!enrolledCourses.contains(selectedCourse)) {
            enrolledCourses.add(selectedCourse);
            System.out.println("Registered successfully for: " + selectedCourse.getTitle());
        } else {
            System.out.println("Already registered for this course.");
        }
    }

    public void accessMaterial(Scanner sc) {
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not registered in any course.");
            return;
        }

        System.out.println("\nYour Courses:");
        for (int i = 0; i < enrolledCourses.size(); i++) {
            System.out.println((i + 1) + ". " + enrolledCourses.get(i).getTitle());
        }

        System.out.print("Select course to view material: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > enrolledCourses.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        enrolledCourses.get(choice - 1).showMaterials();
    }

    public void giveExam(Scanner sc) {
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not registered in any course.");
            return;
        }

        System.out.println("\nCourses with Exams:");
        for (int i = 0; i < enrolledCourses.size(); i++) {
            System.out.println((i + 1) + ". " + enrolledCourses.get(i).getTitle());
        }

        System.out.print("Select course to take exam: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > enrolledCourses.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Course selectedCourse = enrolledCourses.get(choice - 1);

        if (selectedCourse.getExam() == null) {
            System.out.println("No exam available for this course.");
            return;
        }

        Result result = selectedCourse.getExam().conductExam(sc);
        courseResults.put(selectedCourse, result);
        System.out.println("Exam completed. Your Score: " + result.getScore());
    }

    public void viewResults() {
        if (courseResults.isEmpty()) {
            System.out.println("No results available.");
            return;
        }

        System.out.println("\n--- Your Results ---");
        for (Map.Entry<Course, Result> entry : courseResults.entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue().getScore());
        }
    }

    @Override
    public void showMenu() {

        System.out.println("Student menu will be handled from Main based on context.");
    }
}
