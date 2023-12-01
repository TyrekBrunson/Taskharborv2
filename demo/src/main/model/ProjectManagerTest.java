import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;


public class ProjectManagerTest {

    @Test
    public void testGetInstance() {
        ProjectManager instance1 = ProjectManager.getInstance();
        ProjectManager instance2 = ProjectManager.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
   public void testAddProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        Project project = new Project("Test Project", null, new ArrayList<>());
        projectManager.addProject(project);
        assertTrue(projectManager.getAllProjects().contains(project));
    }

    @Test
   public void testRemoveProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        Project project = new Project("Test Project", null, new ArrayList<>());
        projectManager.addProject(project);
        projectManager.removeProject(project);
        assertFalse(projectManager.getAllProjects().contains(project));
    }

    @Test
   public void testAddTask() {
        ProjectManager projectManager = ProjectManager.getInstance();
        Project project = new Project("Test Project", null, new ArrayList<>());
        projectManager.addProject(project);
        Column column = new Column();
        project.getColumns().add(column);

        assertTrue(projectManager.addTask(0, 0, "Test Task", "Task Description"));
        //assertEquals(1, column.getTasks().size());
    }

    @Test
   public void testGetProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        Project project = new Project("Test Project", null, new ArrayList<>());
        projectManager.addProject(project);

        assertEquals(project, projectManager.getProject(0));
    }

    @Test
   public void testAddComment() {
        ProjectManager projectManager = ProjectManager.getInstance();
        Project project = new Project("Test Project", null, new ArrayList<>());
        projectManager.addProject(project);
        Column column = new Column();
        project.getColumns().add(column);

        assertTrue(projectManager.addCommet(0, 0, "Test Comment"));
        //assertEquals(1, column.getComments().size());
    }
}
