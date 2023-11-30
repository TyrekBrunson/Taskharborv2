import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.junit.Test;

class DataReaderTest {

    @Test
    void testGetUsers() {
        ArrayList<User> userList = DataReader.getUsers();
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
    }

    @Test
    void testGetProjects() {
        ArrayList<Project> projectList = DataReader.getProjects();
        assertNotNull(projectList);
        assertFalse(projectList.isEmpty());
    }

    @Test
    void testGetColumns() {
        ArrayList<Column> columnList = DataReader.getColumns();
        assertNotNull(columnList);
        assertFalse(columnList.isEmpty());
    }

    @Test
    void testGetTasks() {
        // adding a JSON array for testing
        JSONArray taskListJSON = new JSONArray();
        // Add some mock tasks to the JSON array
        // ...

        ArrayList<Task> taskList = DataReader.getTasks(taskListJSON);
        assertNotNull(taskList);
        
    }

    @Test
    void testGetComments() {
        // Mocking a JSON array for testing
        JSONArray commentListJSON = new JSONArray();
        // Add some mock comments to the JSON array
        // ...

        ArrayList<comments> commentList = DataReader.getComments(commentListJSON);
        assertNotNull(commentList);
        
    }
}