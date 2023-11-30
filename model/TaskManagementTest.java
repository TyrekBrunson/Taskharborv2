import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;

public class TaskManagementTest {
    private TaskManagement taskManager;

    @Before
    public void setUp() {
        taskManager = TaskManagement.getInstance();
        Task task = new Task("Test Task", 1, new Date(), "Test notes", new ArrayList<>());
        taskManager.addTask(task);
    }

    @Test
    public void testAddTask() {
        Task task = new Task("Task 1", 1, new Date(), "Task notes", new ArrayList<>());
        assertTrue(taskManager.addTask(task));
    }

    @Test
    public void testRemoveTask() {
        Task task = new Task("Task 2", 2, new Date(), "Task notes", new ArrayList<>());
        taskManager.addTask(task);
        assertTrue(taskManager.removeTask("Task 2"));
        assertFalse(taskManager.removeTask("Nonexistent Task"));
    }

    @Test
    public void testGetTask() {
        Task task = new Task("Task 3", 3, new Date(), "Task notes", new ArrayList<>());
        taskManager.addTask(task);
        assertEquals(task, taskManager.getTask("Task 3"));
        assertNull(taskManager.getTask("Nonexistent Task"));
    }

    @Test
    public void testEditTaskPriority() {
        Task task = new Task("Task 4", 4, new Date(), "Task notes", new ArrayList<>());
        taskManager.addTask(task);
        assertTrue(taskManager.editTaskPriority("Task 4", 5));
        assertEquals(5, taskManager.getTask("Task 4").getTaskPriority());
    }

    @Test
    public void testEditTask() {
        Task task = new Task("Task 5", 5, new Date(), "Task notes", new ArrayList<>());
        taskManager.addTask(task);
        assertTrue(taskManager.editTask("Task 5", "New Task Name", 6, new ArrayList<>(), new Date(), "New Task Notes"));
        Task editedTask = taskManager.getTask("New Task Name");
        assertNotNull(editedTask);
        assertEquals(6, editedTask.getTaskPriority());
        assertEquals("New Task Notes", editedTask.getTaskNotes());
    }

    @Test
    public void testMarkTaskComplete() {
        Task task = new Task("Task 6", 6, new Date(), "Task notes", new ArrayList<>());
        taskManager.addTask(task);
        assertTrue(taskManager.markTaskComplete("Task 6"));
    }

    @Test
    public void testMarkTaskWorkingOn() {
        Task task = new Task("Task 7", 7, new Date(), "Task notes", new ArrayList<>());
        taskManager.addTask(task);
        taskManager.markTaskComplete("Task 7");
        assertTrue(taskManager.markTaskWorkingOn("Task 7"));
    }
}