package edu.course.lab06;

import java.util.*;

public class Analytics {

    public Map<String, Integer> countByStatus(List<Task> tasks) {
        Map<String, Integer> result = new HashMap<>();
        for (Task task : tasks) {
            result.put(task.getStatus(), result.getOrDefault(task.getStatus(), 0) + 1);
        }
        return result;
    }

    public Set<String> uniqueCategories(List<Task> tasks) {
        Set<String> categories = new HashSet<>();
        for (Task task : tasks) {
            categories.add(task.getCategory());
        }
        return categories;
    }

    public List<Task> sortByHoursAndId(List<Task> tasks) {
        List<Task> sorted = new ArrayList<>(tasks);
        sorted.sort(Comparator.comparingInt(Task::getHours).thenComparing(Task::getId));
        return sorted;
    }
}