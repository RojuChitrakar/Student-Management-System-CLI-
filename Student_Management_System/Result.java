package Student_Management_System;

public class Result {
    private int score;
    private int total;

    public Result(int score, int total) {
        this.score = score;
        this.total = total;
    }

    public String getScore() {
        if (total == 0) return "No questions";
        int percentage = (score * 100) / total;
        String status = percentage >= 40 ? "Pass" : "Fail";
        return score + "/" + total + " (" + percentage + "%) - " + status;
    }

    public int getRawScore() {
        return score;
    }

    public int getTotalQuestions() {
        return total;
    }

    public int getPercentage() {
        if (total == 0) return 0;
        return (score * 100) / total;
    }
}

