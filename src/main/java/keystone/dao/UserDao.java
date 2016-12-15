package keystone.dao;

import keystone.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserDao {
    /**
     * Creates a new user in the database
     *
     * @param user the user's details
     */
    void createUser(User user);
}
