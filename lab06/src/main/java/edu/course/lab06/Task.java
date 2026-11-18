package edu.course.lab06;

public class Task {
    private final String id;
    private final String category;
    private final String status;
    private final int hours;

    public Task(String id, String category, String status, int hours) {
        this.id = id;
        this.category = category;
        this.status = status;
        this.hours = hours;
    }

    public String getId() { return id; }
    public String getCategory() { return category; }
    public String getStatus() { return status; }
    public int getHours() { return hours; }
}