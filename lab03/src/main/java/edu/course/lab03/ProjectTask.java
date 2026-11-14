package edu.course.lab03;

public class ProjectTask {
    private final String title;
    private final TaskStatus status;
    private final int estimatedHours;

    public ProjectTask(String title, TaskStatus status, int estimatedHours) {
        this.title = title;
        this.status = status;
        this.estimatedHours = estimatedHours;
    }

    public String getTitle() { return title; }
    public TaskStatus getStatus() { return status; }
    public int getEstimatedHours() { return estimatedHours; }
}