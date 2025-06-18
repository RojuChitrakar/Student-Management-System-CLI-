package Student_Management_System;

import java.util.*;

public class Course {
    private String title;
    private String description;
    private int duration;
    private List<String> materials = new ArrayList<>();
    private Exam exam;

    public Course(String title, String description, int duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }


    public void addMaterial(String material) {
        materials.add(material);
    }
    public void showMaterials() {
        System.out.println("\nTraining materials for: " + title);
        if (materials.isEmpty()) {
            System.out.println("No materials available.");
            return;
        }
        for (int i = 0; i < materials.size(); i++) {
            System.out.println((i + 1) + ". " + materials.get(i));
        }
    }

    public Exam getExam() {
        return exam;
    }


    public void setExam(Exam exam) {
        this.exam = exam;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }
}

