package keystone.dao;

import keystone.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    /**
     * Creates a new user in the database
     *
     * @param user the user's details
     */
    void createUser(User user);

    /**
     * Updates the user details
     *
     * @param user the user's data
     */
    void updateUser(User user);

    /**
     * Updates the user's name
     *
     * @param id   of the user
     * @param name new user's name
     */
    void updateName(Long id, String name);

    /**
     * Updates the user's email address
     *
     * @param id    of the user
     * @param email the new email address
     */
    void updateEmail(Long id, String email);

    /**
     * Updates the user's password
     *
     * @param id       of the user
     * @param password the new password
     */
    void updatePassword(Long id, String password);

    /**
     * Deletes the user
     *
     * @param id of the user
     */
    void deleteUser(Long id);


    /**
     * Fetches the user from the database
     *
     * @param id of the user
     * @return the user
     */
    User getUserById(Long id);

    /**
     * Fetches the user with the given email
     *
     * @param email user's email address
     * @return the user which has the given email address
     */
    keystone.dto.User getUserByEmail(String email);
}
