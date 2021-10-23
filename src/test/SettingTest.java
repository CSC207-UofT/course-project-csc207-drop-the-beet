import org.junit.*;
import static org.junit.Assert.*;

public class SettingTest {
    Setting s;
    UserManager um;

    @Before
    public void setUp() {
        um = new UserManager();
        um.addNewUser("u1", "11", "1111");
        um.addNewUser("u2", "22", "2222");
        s = new Setting(um);
    }

    @Test
    public void testUserRegister() {
        assertFalse(s.userRegister("u1", "1", "1"));  // u1 taken
        assertTrue(s.userRegister("u3", "33", "3333"));  // u3 not taken
    }

    @Test
    public void testUserLogIn() {
        assertFalse(s.userLogIn("u1", "2222"));  // password incorrect
        assertFalse(s.userLogIn("u3445", "2222"));  // username does not exist
        assertNull(s.um.getCurrentUser());  // u1 not logged in, currentUser is null

        assertTrue(s.userLogIn("u1", "1111"));  // u1 now is logged in
        assertEquals("u1", s.um.getCurrentUser().getName());

        assertFalse(s.userLogIn("u3", "3333"));  // u1 is logged in, u3 can't be logged in
    }

    @Test
    public void testUserLogOff() {
        assertFalse(s.userLogOff());  // no currU
        s.userLogIn("u1", "1111");  // currU is u1
        assertFalse(s.userLogIn("u2", "2222"));

        assertTrue(s.userLogOff());  // logged off
        assertTrue(s.userLogIn("u2", "2222"));
        assertEquals("u2", s.um.getCurrentUser().getName());  // currU is u2
    }

    @Test
    public void testUserChangePassword() {
        assertFalse(s.userChangePassword("1234", "34567"));  // no currU
        s.userLogIn("u1", "1111");  // currU is u1
        assertFalse(s.userChangePassword("1123", "12345"));  // input password wrong
        assertTrue(s.userChangePassword("1111", "12345678"));
        assertEquals("12345678", s.um.getCurrentUser().getPassword());
    }
}
