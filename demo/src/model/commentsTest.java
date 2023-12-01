import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class commentsTest {

    private comments comments;

    @Before
    public void setup() {
        comments = new comments("John", "User Comments");
    }

    @After
    public void tearDown() {
        comments = null;
    }

    @Test
    public void testAddComment() {
        comments.addComment("Comment 1");
        comments.addComment("Comment 2");

        ArrayList<String> commentList = comments.getComment();

        assertEquals(2, commentList.size());
        assertTrue(commentList.contains("Comment 1"));
        assertTrue(commentList.contains("Comment 2"));
    }

    @Test
    public void testRemoveComment() {
        comments.addComment("Comment 1");
        comments.addComment("Comment 2");

        boolean removed = comments.removeComment("Comment 1");
        assertTrue(removed);

        ArrayList<String> commentList = comments.getComment();

        assertEquals(1, commentList.size());
        assertFalse(commentList.contains("Comment 1"));
        assertTrue(commentList.contains("Comment 2"));
    }

    @Test
    public void testGetUser() {
        assertEquals("John", comments.getUser());
    }

    @Test
    public void testGetCommentText() {
        assertEquals("User Comments", comments.getCommentText());
    }

    @Test
    public void testToString() {
        String expectedString = "Comments{user=John, comments=[]}";
        assertEquals(expectedString, comments.toString());
    }
}

