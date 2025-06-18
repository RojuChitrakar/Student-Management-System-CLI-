package Student_Management_System;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create admin and course list
        Admin admin = new Admin("Admin", "admin@example.com", "admin123");
        List<Course> allCourses = new ArrayList<>();

        // ======= Preload Courses =======
        Course javaCourse = admin.createCourse("Java Basics", "Intro to Java programming", 30);
        Course pythonCourse = admin.createCourse("Python Fundamentals", "Core Python syntax and logic", 25);
        Course webCourse = admin.createCourse("Web Development", "HTML, CSS, JavaScript essentials", 35);

        allCourses.add(javaCourse);
        allCourses.add(pythonCourse);
        allCourses.add(webCourse);

        // ======= Add Training Materials =======
        admin.addMaterial(javaCourse, "Java_Notes.pdf");
        admin.addMaterial(javaCourse, "https://docs.oracle.com/javase/tutorial/");
        admin.addMaterial(pythonCourse, "Python_CheatSheet.pdf");
        admin.addMaterial(pythonCourse, "https://realpython.com/");
        admin.addMaterial(webCourse, "Web_Dev_Essentials.pdf");
        admin.addMaterial(webCourse, "https://developer.mozilla.org/en-US/");

        // ======= Add Exam Questions =======
        preloadQuestions(admin, javaCourse, pythonCourse, webCourse);

        // ======= Entry Menu =======
        System.out.println("Welcome to the Student Management System!");
        System.out.print("Are you a Student or Admin? (S/A): ");
        String role = sc.nextLine().trim().toUpperCase();

        if (role.equals("S")) {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Create a password: ");
            String password = sc.nextLine();

            Student student = new Student(name, email, password);
            System.out.println("Student Registered Successfully!");

            while (true) {
                System.out.println("\n--- Student Menu ---");
                System.out.println("1. Register for a course");
                System.out.println("2. View course materials");
                System.out.println("3. Give exam");
                System.out.println("4. View results");
                System.out.println("5. Exit");

                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        student.registerCourse(allCourses, sc);
                        break;
                    case 2:
                        student.accessMaterial(sc);
                        break;
                    case 3:
                        student.giveExam(sc);
                        break;
                    case 4:
                        student.viewResults();
                        break;
                    case 5:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } else if (role.equals("A")) {
            System.out.print("Enter admin password: ");
            String pass = sc.nextLine();

            if (!admin.checkPassword(pass)) {
                System.out.println("Incorrect password.");
                return;
            }

            System.out.println("Admin Logged In!");

            while (true) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add course");
                System.out.println("2. Add training material");
                System.out.println("3. Add exam question");
                System.out.println("4. Delete course");
                System.out.println("5. View all courses");
                System.out.println("6. Exit");

                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Course Title: ");
                        String title = sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Duration (days): ");
                        int dur = sc.nextInt();
                        sc.nextLine();
                        allCourses.add(admin.createCourse(title, desc, dur));
                        System.out.println("Course added.");
                        break;

                    case 2:
                        showCourses(allCourses);
                        System.out.print("Select course: ");
                        int mIndex = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter material (e.g. link or PDF name): ");
                        String mat = sc.nextLine();
                        admin.addMaterial(allCourses.get(mIndex - 1), mat);
                        System.out.println("Material added.");
                        break;

                    case 3:
                        showCourses(allCourses);
                        System.out.print("Select course: ");
                        int qIndex = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter question: ");
                        String qText = sc.nextLine();

                        List<String> options = new ArrayList<>();
                        for (int i = 1; i <= 3; i++) {
                            System.out.print("Option " + i + ": ");
                            options.add(sc.nextLine());
                        }

                        System.out.print("Enter correct option number (1-3): ");
                        int correct = sc.nextInt();
                        sc.nextLine();

                        admin.addQuestionToExam(allCourses.get(qIndex - 1),
                                new Question(qText, options, correct));
                        System.out.println("Question added.");
                        break;

                    case 4:
                        admin.deleteCourse(allCourses, sc);
                        break;

                    case 5:
                        showCourses(allCourses);
                        break;

                    case 6:
                        System.out.println("Admin logged out.");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } else {
            System.out.println("Invalid user type. Please restart.");
        }
    }

    private static void showCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("\n--- Course List ---");
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.println((i + 1) + ". " + c.getTitle() + " - " + c.getDescription());
        }
    }

    private static void preloadQuestions(Admin admin, Course java, Course python, Course web) {
        // Java Questions
        admin.addQuestionToExam(java, new Question("What is JVM?", List.of("Java Virtual Machine", "Java Version Manager", "Java Visual Mapper"), 1));
        admin.addQuestionToExam(java, new Question("Which keyword is used to inherit a class?", List.of("super", "extends", "this"), 2));
        admin.addQuestionToExam(java, new Question("Which method is the entry point in Java?", List.of("start()", "run()", "main()"), 3));
        admin.addQuestionToExam(java, new Question("Which keyword creates an object?", List.of("make", "object", "new"), 3));
        admin.addQuestionToExam(java, new Question("Which is not a primitive type?", List.of("int", "String", "boolean"), 2));
        admin.addQuestionToExam(java, new Question("Which loop is entry-controlled?", List.of("do-while", "while", "foreach"), 2));
        admin.addQuestionToExam(java, new Question("Which class is used for output?", List.of("Scanner", "PrintStream", "InputStream"), 2));
        admin.addQuestionToExam(java, new Question("Which operator is used for comparison?", List.of("=", "==", "==="), 2));
        admin.addQuestionToExam(java, new Question("Exception class belongs to which package?", List.of("java.io", "java.util", "java.lang"), 3));
        admin.addQuestionToExam(java, new Question("Which of these is an interface?", List.of("Runnable", "Thread", "Object"), 1));

        // Python Questions
        admin.addQuestionToExam(python, new Question("Which data type is immutable?", List.of("List", "Set", "Tuple"), 3));
        admin.addQuestionToExam(python, new Question("Which symbol is used for comments?", List.of("#", "//", "/* */"), 1));
        admin.addQuestionToExam(python, new Question("What does len() do?", List.of("Gives length", "Deletes item", "Sorts list"), 1));
        admin.addQuestionToExam(python, new Question("What is the output of 2**3?", List.of("6", "8", "9"), 2));
        admin.addQuestionToExam(python, new Question("Which is a Python keyword?", List.of("define", "function", "def"), 3));
        admin.addQuestionToExam(python, new Question("Which method adds an element to a list?", List.of("add()", "insert()", "append()"), 3));
        admin.addQuestionToExam(python, new Question("How do you create a function?", List.of("function myFunc():", "def myFunc():", "create myFunc():"), 2));
        admin.addQuestionToExam(python, new Question("Which file extension is for Python?", List.of(".java", ".py", ".js"), 2));
        admin.addQuestionToExam(python, new Question("How do you print in Python?", List.of("echo", "console.log", "print"), 3));
        admin.addQuestionToExam(python, new Question("Which data type is a dictionary?", List.of("{}", "[]", "()"), 1));

        // Web Dev Questions
        admin.addQuestionToExam(web, new Question("Which tag creates a hyperlink?", List.of("<a>", "<link>", "<href>"), 1));
        admin.addQuestionToExam(web, new Question("CSS stands for?", List.of("Computer Style Sheets", "Cascading Style Sheets", "Creative Style System"), 2));
        admin.addQuestionToExam(web, new Question("Which HTML tag adds a line break?", List.of("<break>", "<lb>", "<br>"), 3));
        admin.addQuestionToExam(web, new Question("Which property changes text color in CSS?", List.of("text-color", "font-color", "color"), 3));
        admin.addQuestionToExam(web, new Question("Which tag creates a list?", List.of("<ul>", "<table>", "<div>"), 1));
        admin.addQuestionToExam(web, new Question("JavaScript is a ___ language.", List.of("compiled", "programming", "scripting"), 3));
        admin.addQuestionToExam(web, new Question("What does DOM stand for?", List.of("Document Object Model", "Data Object Map", "DOM Parser"), 1));
        admin.addQuestionToExam(web, new Question("Which symbol starts an ID selector in CSS?", List.of(".", "*", "#"), 3));
        admin.addQuestionToExam(web, new Question("What is the correct syntax for an image tag?", List.of("<img src=''>", "<image src=''>", "<pic src=''>"), 1));
        admin.addQuestionToExam(web, new Question("Which tag is used to create a form?", List.of("<form>", "<input>", "<div>"), 1));
    }
}