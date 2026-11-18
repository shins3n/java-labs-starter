package edu.course.lab05;

import java.util.ArrayList;
import java.util.List;

public final class LoadResult {
    private final List<Task> validTasks = new ArrayList<>();
    private final List<ValidationError> errors = new ArrayList<>();

    public void addValidTask(Task task) {
        validTasks.add(task);
    }

    public void addError(ValidationError error) {
        errors.add(error);
    }

    public List<Task> getValidTasks() {
        return validTasks;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}