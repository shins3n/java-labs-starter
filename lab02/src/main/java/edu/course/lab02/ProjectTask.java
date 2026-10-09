package edu.course.lab02;

public class ProjectTask {

    private final String id;
    private final String title;
    private TaskStatus status;
    private int estimatedHours;

    public ProjectTask(String id, String title, TaskStatus status, int estimatedHours) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id не может быть null или пустым");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title не может быть null или пустым");
        }
        if (status == null) {
            throw new IllegalArgumentException("status не может быть null");
        }
        if (estimatedHours <= 0) {
            throw new IllegalArgumentException("estimatedHours должен быть положительным");
        }
        this.id = id;
        this.title = title;
        this.status = status;
        this.estimatedHours = estimatedHours;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void changeStatus(TaskStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Новый статус не может быть null");
        }
        this.status = newStatus;
    }

    public boolean isCompleted() {
        return status == TaskStatus.DONE;
    }

    public void increaseEstimatedHours(int extraHours) {
        if (extraHours <= 0) {
            throw new IllegalArgumentException("Дополнительные часы должны быть положительными");
        }
        this.estimatedHours += extraHours;
    }
}