import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;

public class TaskTest {
    private Task task;
    private ArrayList<comments> commentsList;

    @Before
    public void setUp() {
        commentsList = new ArrayList<>();
        commentsList.add(new comments("AttiucsM", "Comment 1"));
        commentsList.add(new comments("DwayneW", "Comment 2"));
        task = new Task("Task 1", 2, new Date(), "Task notes", commentsList);
    }

    @Test
    public void testGetTaskName() {
        assertEquals("Task 1", task.getTaskName());
    }

    @Test
    public void testSetTaskName() {
        task.setTaskName("New Task Name");
        assertEquals("New Task Name", task.getTaskName());
    }

    @Test
    public void testGetTaskPriority() {
        assertEquals(2, task.getTaskPriority());
    }

    @Test
    public void testSetTaskPriority() {
        task.setTaskPriority(3);
        assertEquals(3, task.getTaskPriority());
    }

    @Test
    public void testGetTaskDueDate() {
        assertNotNull(task.getTaskDueDate());
    }

    @Test
    public void testGetTaskNotes() {
        assertEquals("Task notes", task.getTaskNotes());
    }

    @Test
    public void testSetTaskNotes() {
        task.setTaskNotes("New Task Notes");
        assertEquals("New Task Notes", task.getTaskNotes());
    }

    @Test
    public void testGetComments() {
        assertEquals(commentsList, task.getComments());
    }

    @Test
    public void testAddComment() {
        comments newComment = new comments("JaMyiusC", "New Comment");
        task.addComment(newComment);
        assertTrue(task.getComments().contains(newComment));
    }

    @Test
    public void testRemoveComment() {
        comments commentToRemove = commentsList.get(0);
        assertTrue(task.removeComment(commentToRemove));
        assertFalse(task.getComments().contains(commentToRemove));
    }

    @Test
    public void testToString() {
        String expected = "Task Name: Task 1\nPriority: 2\nDue Date: " + task.getTaskDueDate() + "\nNotes: Task notes\nComments:\nComment 1\nComment 2\n";
        assertEquals(expected, task.toString());
    }
}
