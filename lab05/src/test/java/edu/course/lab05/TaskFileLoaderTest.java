package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskFileLoaderTest {

    @TempDir
    Path tempDir;

    private Path createFile(String... lines) throws IOException {
        Path file = tempDir.resolve("tasks.txt");
        Files.write(file, List.of(lines), StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void loadsValidFile() throws IOException {
        Path file = createFile(
                "id;title;category;estimatedHours;status",
                "T-1;Task 1;backend;8;READY",
                "T-2;Task 2;frontend;4;DONE"
        );
        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(2, result.getValidTasks().size());
        assertEquals(0, result.getErrors().size());
    }

    @Test
    void handlesUnicodeText() throws IOException {
        Path file = createFile(
                "id;title;category;estimatedHours;status",
                "T-1;Привет мир;категория;5;IN_PROGRESS"
        );
        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(1, result.getValidTasks().size());
        assertEquals("Привет мир", result.getValidTasks().get(0).getTitle());
    }

    @Test
    void reportsEmptyField() throws IOException {
        Path file = createFile(
                "id;title;category;estimatedHours;status",
                ";Title;cat;5;READY"
        );
        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(0, result.getValidTasks().size());
        assertEquals(1, result.getErrors().size());
    }

    @Test
    void reportsInvalidNumber() throws IOException {
        Path file = createFile(
                "id;title;category;estimatedHours;status",
                "T-1;Title;cat;abc;READY"
        );
        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(1, result.getErrors().size());
    }

    @Test
    void reportsUnknownStatus() throws IOException {
        Path file = createFile(
                "id;title;category;estimatedHours;status",
                "T-1;Title;cat;5;UNKNOWN"
        );
        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(1, result.getErrors().size());
    }

    @Test
    void reportsWrongColumnCount() throws IOException {
        Path file = createFile(
                "id;title;category;estimatedHours;status",
                "T-1;Title;cat;5"
        );
        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(1, result.getErrors().size());
    }

    @Test
    void handlesMixOfValidAndInvalidLines() throws IOException {
        Path file = createFile(
                "id;title;category;estimatedHours;status",
                "T-1;Good task;cat;5;READY",
                "T-2;Bad task;cat;abc;READY",
                "T-3;Another good;cat;3;DONE"
        );
        TaskFileLoader loader = new TaskFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(2, result.getValidTasks().size());
        assertEquals(1, result.getErrors().size());
    }

    @Test
    void throwsExceptionForMissingFile() {
        Path missing = tempDir.resolve("no_file.txt");
        TaskFileLoader loader = new TaskFileLoader();

        assertThrows(DataLoadingException.class, () -> loader.load(missing));
    }
}