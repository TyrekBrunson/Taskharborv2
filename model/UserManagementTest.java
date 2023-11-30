import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserManagementTest {

    private UserManagement userManager;

    @Before
    public void setUp() {
        userManager = UserManagement.getInstance();
    }

    @Test
    public void testAddUser() {
        assertTrue(userManager.addUser("John", "Doe", "johndoe", "password"));
        assertTrue(userManager.hasUser("johndoe", "password"));
    }

    @Test
    public void testRemoveUser() {
        userManager.addUser("Alice", "Smith", "alicesmith", "password");
        assertTrue(userManager.removeUser("alicesmith", "password"));
        assertFalse(userManager.hasUser("alicesmith", "password"));
    }

    @Test
    public void testGetUser() {
        userManager.addUser("Bob", "Johnson", "bjohnson", "secret");
        User user = userManager.getUser("bjohnson", "secret");
        assertNotNull(user);
        assertEquals("Bob", user.getFirstName());
        assertEquals("Johnson", user.getLastName());
    }

    @Test
    public void testEditUserPassword() {
        userManager.addUser("Eve", "Wilson", "ewilson", "oldpassword");
        assertTrue(userManager.hasUser("ewilson", "newpassword"));
    }

    @Test
    public void testSaveUsers() {
        
    }
}