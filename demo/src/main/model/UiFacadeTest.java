import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class UiFacadeTest {

    private UiFacade uiFacade;
    private User testUser;


    @Before
    public void setUp() {
        uiFacade = new UiFacade();

        testUser = new User("TestFirstName", "TestLastName", "TestUser", "TestPassword");

        UserManagement.getInstance();
        UserManagement.getInstance().addUser("TestFirstName", "TestLastName", "TestUser", "TestPassword");

        uiFacade.login("TestUser", "TestPassword");
    }

    @Test
    public void testCreateAccount() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String userName = "johndoe";
        String password = "password";

        // Act
        boolean accountCreated = uiFacade.createAccount(firstName, lastName, userName, password);

        // Assert
        assertTrue(accountCreated);
    }

    @Test
    public void testLogin() {
        // Arrange
        String userName = "TestUser";
        String password = "TestPassword";

        // Act
        boolean loginSuccess = uiFacade.login(userName, password);

        // Assert
        assertTrue(loginSuccess);
        assertNotNull(uiFacade.getCurrentUser());
    }

    @Test
    public void testLogout() {
        // Act
        uiFacade.logout();

        // Assert
        assertNull(uiFacade.getCurrentUser());
    }

    @Test
    public void testAddProject() {
        // Arrange
        String projectName = "TestProject";
        Date projectDate = new Date();
        ArrayList<Column> columns = new ArrayList<>();

        // Act
        boolean projectAdded = uiFacade.addProject(projectName, projectDate, columns);

        // Assert
        assertTrue(projectAdded);
        assertEquals(1, uiFacade.getCurrentUser().getProjectManager().getAllProjects().size());
    }

    @Test
    public void testRemoveProject() {
        // Arrange
        uiFacade.addProject("TestProject", new Date(), new ArrayList<>());

        // Act
        boolean projectRemoved = uiFacade.removeProject(0);

        // Assert
        assertTrue(projectRemoved);
        assertEquals(0, uiFacade.getCurrentUser().getProjectManager().getAllProjects().size());
    }

    @Test
    public void testAddColumn() {
        // Arrange
        uiFacade.addProject("TestProject", new Date(), new ArrayList<>());

        // Act
        boolean columnAdded = uiFacade.addColumn(0, "TestColumn");

        // Assert
        assertTrue(columnAdded);
        assertEquals(1, uiFacade.getCurrentUser().getProjectManager().getAllProjects().get(0).getColumns().size());
    }
}
