package keystone.service;

import keystone.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * Creates a new user
     *
     * @param user a dto with the user's details (name, email, id, password)
     */
    void createUser(User user);
}
