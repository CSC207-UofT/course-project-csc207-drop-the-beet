//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import com.planner.Entities.User;
//import com.planner.UseCases.UserManager;
//
//
//public class UserManagerTest {
//    UserManager um;
//
//    @Before
//    public void setUp() {
//        um = new UserManager();
//        assertTrue(um.addNewUser("User1", "2", "3"));
//    }
//
//    @Test
//    public void testInitialCurrentUser() {
//        assertNull(um.getCurrentUser());
//    }
//
//    @Test
//    public void testAddNewUser() {
//        assertEquals(1, um.getUserHashmap().size());
//        assertTrue(um.addNewUser("User2", "22", "33"));
//        assertFalse(um.addNewUser("User1", "222", "333"));
//        assertEquals(2, um.getUserHashmap().size());
//    }
//
//    @Test
//    public void testContainsUser() {
//        assertTrue(um.containsUser("User1"));
//        assertFalse(um.containsUser("User3"));
//    }
//
//    @Test
//    public void testGetUser() {
//        assertEquals("2", um.getUser("User1").getEmail());
//        assertNull(um.getUser("User3"));
//    }
//}
