import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {
    
    @Test
    public void testCheckPassword() {
        User user = new User("John", "Doe", "johndoe", "password123");

        assertTrue(user.checkPassword("password123"));
        assertFalse(user.checkPassword("wrongpassword"));
    }

    @Test
    public void testUpdatePassword() {
        User user = new User("John", "Doe", "johndoe", "password123");

        assertTrue(user.updatePassword("newPassword456"));
        assertEquals("newPassword456", user.getPassword());
    }

    @Test
    public void testConfirmUser() {
        User user = new User("John", "Doe", "johndoe", "password123");

        assertTrue(user.confirmUser("johndoe", "password123"));
        assertFalse(user.confirmUser("johndoe", "wrongpassword"));
        assertFalse(user.confirmUser("anotheruser", "password123"));
    }

    @Test
    public void testSaveToJsonArray() {
        
    }
}
