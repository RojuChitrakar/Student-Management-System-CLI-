package Student_Management_System;

import java.util.*;

public class Exam {
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Result conductExam(Scanner sc) {
        if (questions.isEmpty()) {
            System.out.println("No questions set for this exam.");
            return new Result(0, 0);
        }

        int score = 0;

        System.out.println("\n--- Starting Exam ---");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.getQuestionText());
            List<String> options = q.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            System.out.print("Your answer (1-" + options.size() + "): ");
            int answer = sc.nextInt();
            sc.nextLine();

            if (answer == q.getCorrectOption()) {
                score++;
            }
        }

        System.out.println("Exam finished.");
        return new Result(score, questions.size());
    }

    public void showAllQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).getQuestionText());
        }
    }
}

