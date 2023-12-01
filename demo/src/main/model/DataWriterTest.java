import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.junit.Test;
public class DataWriterTest {
    private User user = UserManagement.getInstance().getUser("mpatel", "meet2002...");
    private ArrayList<User> usersList = UserManagement.getInstance().getUserList();
    @Test
    void testSaveUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Meet", "Patel", "mpatel", "meet2002..."));
        DataWriter.saveUsers();
    }

    @Test
    void testSaveTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
    }

    @Test
    void testSaveColumns() {
        ArrayList<Task> columns = new ArrayList<>();
        assertNotNull(columns);
        assertFalse(columns.isEmpty());
    }

    @Test
    void testSaveProjects() {
        ArrayList<Task> projects = new ArrayList<>();
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
    }
}