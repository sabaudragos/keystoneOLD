package keystone.rest;

import keystone.KeystoneConfigTest;
import keystone.dao.UserDaoImpl;
import keystone.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KeystoneConfigTest.class)
public class UserDaoImplTest extends AbstractJUnit4SpringContextTests {

    private static final String USER_NAME = "John Doe";
    private static final String USER_EMAIL = "john@doe.com";
    private static final String USER_PASSWORD = "bad password";
    @Resource
    SingleConnectionDataSource singleConnectionDataSource;
    private User user;
    @Resource
    private UserDaoImpl userDaoImpl;

    @Before
    public void setup() throws SQLException {
        user = buildUser(USER_NAME, USER_EMAIL, USER_PASSWORD);
    }

    @Test
    public void shouldCreateUser() throws SQLException {
        // given

        // when
        userDaoImpl.createUser(user);

        // then
        keystone.dto.User userDto = userDaoImpl.getUserByEmail(USER_EMAIL);
        assertEquals("User name should match", USER_NAME, userDto.getName());
        assertEquals("User email should match", USER_EMAIL, userDto.getEmail());
        assertEquals("User password should match", USER_PASSWORD, userDto.getPassword());
    }

    @Test
    public void shouldDeleteUser() {
        // given
        userDaoImpl.createUser(user);
        Long userId = userDaoImpl.getUserByEmail(USER_EMAIL).getId();

        //when
        userDaoImpl.deleteUser(userId);

        //then
        userId = userDaoImpl.getUserByEmail(USER_EMAIL).getId();
        assertTrue(userId == null);
    }

    @Test
    public void shouldGetUserById() {
        // given
        userDaoImpl.createUser(user);
        Long userId = userDaoImpl.getUserByEmail(USER_EMAIL).getId();

        //when
        User userFromDb = userDaoImpl.getUserById(userId);

        //then
        assertEquals("User name should match", user.getName(), userFromDb.getName());
        assertEquals("User email should match", user.getEmail(), userFromDb.getEmail());
        assertEquals("User password should match", user.getPassword(), userFromDb.getPassword());
        assertEquals("User id should match", userId, userFromDb.getId());

    }

    @Test
    public void shouldUpdateUser() {

    }

    @Test
    public void shouldUpdateUserName() {

    }

    @Test
    public void shouldUpdateUserEmail() {

    }

    @Test
    public void shouldUpdateUserPassword() {

    }

    private User buildUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
