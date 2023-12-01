import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;

public class ColumnTest {

    private Column column;

    @BeforeClass
    public void setup() {
        column = new Column();
    }

    @AfterClass
    public void tearDown() {
        column = null;
    }

    @Test
    void testGetColumnName() {
        assertEquals("Default Column Name", column.getColumnName());
    }

    @Test
    void testSetColumnName() {
        column.setColumnName("Test Column Name");
        assertEquals("Test Column Name", column.getColumnName());
    }

    @Test
    void testAddColumnTask() {
        Task task = new Task(String);
        assertTrue(column.addColumnTask(task));
        assertTrue(column.getColumnTaskList().contains(task));
    }

    @Test
    void testRemoveColumnTask() {
        Task task = new Task(null, 0, null, null, null);
        column.addColumnTask(task);
        assertTrue(column.removeColumnTask(task));
        assertFalse(column.getColumnTaskList().contains(task));
    }

    @Test
    void testAddComment() {
        comments comment = new comments(null, null);
        column.addComment(comment);
        assertTrue(column.getcommentsList().contains(comment));
    }

    @Test
    void testGetPosition() {
        assertEquals(0, column.getPosition());
    }

    @Test
    void testToString() {
        String expectedString = "Column Name: Default Column Name\n" +
                "Column Position: 0\n" +
                "Column Task List: []\n";
        assertEquals(expectedString, column.toString());
    }
}
