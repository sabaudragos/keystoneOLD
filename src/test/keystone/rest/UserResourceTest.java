package keystone.rest;

import keystone.dto.User;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class UserResourceTest {

    private final String VALID_PASSWORD = "Valid_Password_2016";
    private final String INVALID_PASSWORD = "bad";

    private UserResource userResource = new UserResource();

    @Test
    public void shouldReturnFalseWhenNullOrEmptyPassword() {
        //given
        String emptyPassword = "";
        String nullPassword = null;

        //when
        boolean isEmpty = userResource.validatePasswordComplexity(emptyPassword);
        boolean isNull = userResource.validatePasswordComplexity(nullPassword);

        //then
        assertFalse("The password validator should have returned false for " +
                "empty password", isEmpty);
        assertFalse("The password validator should have returned false for " +
                "null password", isNull);

    }

    @Test
    public void shouldReturnFalseWhenPasswordTooShort() {
        //given
        String password = "short";

        //when
        boolean isLong = userResource.validatePasswordComplexity(password);

        //then
        assertFalse("The password validator should have returned false for " +
                "too short password", isLong);
    }

    @Test
    public void shouldReturnFalseWhenNoOrLowerUpperCase() {
        //given
        String noUpperCasePassword = "nouppercase";
        String noLowerCasePassword = "NOLOWERCASE";

        //when
        boolean hasUpperCase = userResource.validatePasswordComplexity(noUpperCasePassword);
        boolean hasLowerCase = userResource.validatePasswordComplexity(noLowerCasePassword);

        //then
        assertFalse("The password validator should have returned false for " +
                "password with no uppercase", hasUpperCase);
        assertFalse("The password validator should have returned false for " +
                "password with no lowercase", hasLowerCase);

    }

    @Test
    public void shouldReturnFalseWhenNoSpecialCharacter() {
        //given
        String specialCharacterPassword = "specialCharacterPassword1984";

        //when
        boolean hasSpecialCharacter = userResource.validatePasswordComplexity(specialCharacterPassword);

        //then
        assertFalse("The password validator should have returned false for " +
                "password with no special characters", hasSpecialCharacter);
    }

    @Test
    public void shouldReturnTrueWhenValidPassword() {
        //given valid password

        //when
        boolean isValid = userResource.validatePasswordComplexity(VALID_PASSWORD);

        //then
        assertTrue("The password is valid, it should be true", isValid);
    }

    @Test
    public void shouldReturnFalseIfNullUser() {
        //given
        User user = null;

        //when
        boolean isValid = userResource.validateUser(user);

        //then
        assertFalse("The user is null, it should be false", isValid);
    }

    @Test
    public void shouldReturnFalseIfNullOrEmptyUserName() {
        //given
        User userOne = new User();
        userOne.setName(null);

        User userTwo = new User();
        userTwo.setName("");

        //when
        boolean isValidOne = userResource.validateUser(userOne);
        boolean isValidTwo = userResource.validateUser(userTwo);

        //then
        assertFalse("The user name is null, it should be false", isValidOne);
        assertFalse("The user name is empty, it should be false", isValidTwo);
    }

    @Test
    public void shouldReturnFalseIfInvalidEmail() {
        //given
        User userOne = new User();
        userOne.setName("John Doe");
        userOne.setPassword(VALID_PASSWORD);
        userOne.setEmail("@mail.com");

        //when
        boolean isValid = userResource.validateUser(userOne);

        //then
        assertFalse("The email is invalid, it should be false", isValid);
    }

    @Test
    public void shouldReturnTrueIfValidUser() {
        //given
        User userOne = new User();
        userOne.setName("John Doe");
        userOne.setPassword(VALID_PASSWORD);
        userOne.setEmail("john@mail.com");

        //when
        boolean isValid = userResource.validateUser(userOne);

        //then
        assertTrue("The user is invalid", isValid);
    }

    @Test
    public void shouldThrowBadRequestWhenInvalidUser() {
        //given
        User userOne = new User();
        userOne.setName("John Doe");
        userOne.setPassword(INVALID_PASSWORD);
        userOne.setEmail("john@mail.com");

        //when
        Response response = userResource.createUser(userOne);

        //then
        assertEquals("The user is invalid, should be BAD REQUEST",
                Response.Status.BAD_REQUEST.getStatusCode(),
                response.getStatus());
        assertEquals("The user is invalid, the message should be " + RestConstants.BAD_REQUEST_INVALID_USER,
                RestConstants.BAD_REQUEST_INVALID_USER,
                response.getEntity());
    }
}
