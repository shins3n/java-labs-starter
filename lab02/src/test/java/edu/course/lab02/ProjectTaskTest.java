package edu.course.lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTaskTest {

    @Test
    void constructorCreatesValidTask() {
        ProjectTask task = new ProjectTask("T1", "Сделать отчёт", TaskStatus.NEW, 5);
        assertEquals("T1", task.getId());
        assertEquals("Сделать отчёт", task.getTitle());
        assertEquals(TaskStatus.NEW, task.getStatus());
        assertEquals(5, task.getEstimatedHours());
    }

    @Test
    void constructorThrowsForNullOrEmptyId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask(null, "Title", TaskStatus.NEW, 5));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("", "Title", TaskStatus.NEW, 5));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("   ", "Title", TaskStatus.NEW, 5));
    }

    @Test
    void constructorThrowsForNullOrEmptyTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T1", null, TaskStatus.NEW, 5));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T1", "", TaskStatus.NEW, 5));
    }

    @Test
    void constructorThrowsForNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T1", "Title", null, 5));
    }

    @Test
    void constructorThrowsForNonPositiveHours() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T1", "Title", TaskStatus.NEW, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("T1", "Title", TaskStatus.NEW, -3));
    }

    @Test
    void changeStatusUpdatesStatus() {
        ProjectTask task = new ProjectTask("T1", "Title", TaskStatus.NEW, 5);
        task.changeStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void changeStatusThrowsForNull() {
        ProjectTask task = new ProjectTask("T1", "Title", TaskStatus.NEW, 5);
        assertThrows(IllegalArgumentException.class, () -> task.changeStatus(null));
    }

    @Test
    void isCompletedReturnsTrueOnlyForDone() {
        ProjectTask task = new ProjectTask("T1", "Title", TaskStatus.NEW, 5);
        assertFalse(task.isCompleted());
        task.changeStatus(TaskStatus.DONE);
        assertTrue(task.isCompleted());
    }

    @Test
    void increaseEstimatedHoursAddsHours() {
        ProjectTask task = new ProjectTask("T1", "Title", TaskStatus.NEW, 5);
        task.increaseEstimatedHours(3);
        assertEquals(8, task.getEstimatedHours());
    }

    @Test
    void increaseEstimatedHoursThrowsForNonPositive() {
        ProjectTask task = new ProjectTask("T1", "Title", TaskStatus.NEW, 5);
        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimatedHours(0));
        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimatedHours(-2));
    }
}