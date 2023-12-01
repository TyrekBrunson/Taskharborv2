import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class changeHistoryTest {

    private static changeHistory changeHistory;

    @BeforeClass
    public static void setup() {
        changeHistory = changeHistory.getInstance();
    }

    @AfterClass
    public void tearDown() {
        changeHistory = null;
    }

    @Test
    void testViewTaskHistory() {
        ArrayList<String> taskHistory = changeHistory.viewTaskHistory();
        assertNotNull(taskHistory);
        assertTrue(taskHistory.isEmpty());
    }

    @Test
    void testAddTaskHistory() {
        changeHistory.addTaskHistory("Task updated: changed due date");
        ArrayList<String> taskHistory = changeHistory.viewTaskHistory();
        assertFalse(taskHistory.isEmpty());
        assertEquals(1, taskHistory.size());
        assertEquals("Task updated: changed due date", taskHistory.get(0));
    }
}