package edu.course.lab03;

public class StatusPriorityPolicy implements PriorityPolicy {
    @Override
    public int calculatePriority(ProjectTask task) {
        return switch (task.getStatus()) {
            case DONE -> 1;
            case IN_PROGRESS -> 5;
            case NEW -> 10;
            case CANCELLED -> 0;
        };
    }
}