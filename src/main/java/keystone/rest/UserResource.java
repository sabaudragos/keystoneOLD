package keystone.rest;

import keystone.dto.User;
import keystone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static keystone.rest.RestConstants.USER_PATH;

@Slf4j
@Path(USER_PATH)
public class UserResource {
    private final int PASSWORD_MIN_SIZE = 6;

    @Resource
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {

        if (!validateUser(user)) {
            log.warn("Invalid user: {}", user);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        userService.createUser(user);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {

        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser() {
        return Response.ok().build();
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") int userId) {
        return Response.noContent().build();
    }

    boolean validateUser(User user) {
        if (user == null) {
            return false;
        }
        if (StringUtils.isEmpty(user.getName())) {
            return false;
        }

        if (!validatePasswordComplexity(user.getPassword())) {
            return false;
        }

        EmailValidator emailValidator = EmailValidator.getInstance();

        if (!emailValidator.isValid(user.getEmail())) {
            return false;
        }

        return true;
    }

    boolean validatePasswordComplexity(String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }

        if (password.length() < PASSWORD_MIN_SIZE) {
            return false;
        }

        if (password.equals(password.toUpperCase())) {
            return false;
        }

        if (password.equals(password.toLowerCase())) {
            return false;
        }

        boolean hasSpecialCharacters = !password.matches("[A-Za-z0-9]*");
        if (!hasSpecialCharacters) {
            return false;
        }

        return true;
    }
}
