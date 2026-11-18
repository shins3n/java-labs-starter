package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TaskFileLoader {

    public LoadResult load(Path path) {
        LoadResult result = new LoadResult();
        List<String> lines;

        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataLoadingException("Cannot read " + path, e);
        }

        if (lines.isEmpty()) {
            return result;
        }

        String expectedHeader = "id;title;category;estimatedHours;status";
        if (!lines.get(0).trim().equals(expectedHeader)) {
            result.addError(new ValidationError(1, "header", "Неверный заголовок файла"));
            return result;
        }

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }

            int lineNumber = i + 1;
            String[] parts = line.split(";");

            if (parts.length != 5) {
                result.addError(new ValidationError(lineNumber, "line", "Неверное количество столбцов"));
                continue;
            }

            String id = parts[0].trim();
            String title = parts[1].trim();
            String category = parts[2].trim();
            String hoursStr = parts[3].trim();
            String status = parts[4].trim();

            if (id.isEmpty() || title.isEmpty()) {
                result.addError(new ValidationError(lineNumber, "id/title", "ID или Title не могут быть пустыми"));
                continue;
            }

            int hours;
            try {
                hours = Integer.parseInt(hoursStr);
                if (hours <= 0) {
                    result.addError(new ValidationError(lineNumber, "estimatedHours", "Часы должны быть положительными"));
                    continue;
                }
            } catch (NumberFormatException e) {
                result.addError(new ValidationError(lineNumber, "estimatedHours", "Неверное число часов"));
                continue;
            }

            if (!status.equals("READY") && !status.equals("IN_PROGRESS") && !status.equals("DONE")) {
                result.addError(new ValidationError(lineNumber, "status", "Неизвестный статус"));
                continue;
            }

            Task task = new Task(id, title, category, hours, status);
            result.addValidTask(task);
        }

        return result;
    }
}