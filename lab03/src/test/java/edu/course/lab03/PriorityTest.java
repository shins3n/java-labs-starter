package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriorityTest {

    @Test
    void effortPolicyCalculatesBasedOnHours() {
        ProjectTask task = new ProjectTask("Task", TaskStatus.NEW, 8);
        EffortPriorityPolicy policy = new EffortPriorityPolicy();
        assertEquals(8, policy.calculatePriority(task));
    }

    @Test
    void statusPolicyCalculatesBasedOnStatus() {
        StatusPriorityPolicy policy = new StatusPriorityPolicy();
        assertEquals(10, policy.calculatePriority(new ProjectTask("T1", TaskStatus.NEW, 5)));
        assertEquals(1, policy.calculatePriority(new ProjectTask("T2", TaskStatus.DONE, 5)));
    }

    @Test
    void prioritizerChoosesMoreImportantTaskBasedOnPolicy() {
        ProjectTask shortTask = new ProjectTask("Short", TaskStatus.NEW, 2);
        ProjectTask longTask = new ProjectTask("Long", TaskStatus.NEW, 10);

        TaskPrioritizer effortPrioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        assertEquals(longTask, effortPrioritizer.chooseMoreImportant(shortTask, longTask));

        TaskPrioritizer statusPrioritizer = new TaskPrioritizer(new StatusPriorityPolicy());
        ProjectTask doneTask = new ProjectTask("Done", TaskStatus.DONE, 2);
        ProjectTask newTask = new ProjectTask("New", TaskStatus.NEW, 10);
        assertEquals(newTask, statusPrioritizer.chooseMoreImportant(doneTask, newTask));
    }
}