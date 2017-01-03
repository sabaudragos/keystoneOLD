package keystone.dao;

import keystone.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private SingleConnectionDataSource singleConnectionDataSource;

    @Resource
    private Mapper mapper;

    @Override
    public void createUser(User user) {
        String query = String.format("INSERT INTO user (name,email,password) VALUES('%s','%s','%s')",
                user.getName(), user.getEmail(), user.getPassword());

        try (PreparedStatement prepareStatement =
                     singleConnectionDataSource.getConnection().prepareStatement(query)) {
            int noOfRowsAffected = prepareStatement.executeUpdate();
            log.info("{} rows were affected", noOfRowsAffected);
        } catch (SQLException e) {
            log.error("Error while adding user to the database. Error: ", e);
        }
    }

    /**
     * Updates the user details
     *
     * @param user the user's data
     */
    @Override
    public void updateUser(User user) {
        String query = String.format("UPDATE user " +
                        "SET name='%s', email='%s', password='%s'" +
                        "WHERE id='%d'", user.getName(), user.getEmail(),
                user.getPassword(), user.getId());

        try (PreparedStatement preparedStatement =
                     singleConnectionDataSource.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
            log.info("The user details were successfully updated. User id {}", user.getId());
        } catch (SQLException e) {
            log.error("Failed to update the user details. User id {}", user.getId());
            e.printStackTrace();
        }
    }

    /**
     * Updates the user's name
     *
     * @param id   of the user
     * @param name new user's name
     */
    @Override
    public void updateName(Long id, String name) {
        String query = String.format("UPDATE user SET name='%s' WHERE id='%d'", name, id);

        try (PreparedStatement preparedStatement =
                     singleConnectionDataSource.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
            log.info("The user name was changed successfully. User id {}", id);
        } catch (SQLException e) {
            log.error("Failed to update the user name. User id {}", id);
            e.printStackTrace();
        }
    }

    /**
     * Updates the user's email address
     *
     * @param id    of the user
     * @param email the new email address
     */
    @Override
    public void updateEmail(Long id, String email) {
        String query = String.format("UPDATE user SET email='%s' WHERE id='%d'", email, id);

        try (PreparedStatement preparedStatement =
                     singleConnectionDataSource.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
            log.info("The email was changed successfully. User id {}", id);
        } catch (SQLException e) {
            log.error("Failed to update the email. User id {}", id);
            e.printStackTrace();
        }
    }

    /**
     * Updates the user's password
     *
     * @param id       of the user
     * @param password the new password
     */
    @Override
    public void updatePassword(Long id, String password) {
        String query = String.format("UPDATE user SET password='%s' WHERE id='%d'", password, id);

        try (PreparedStatement preparedStatement =
                     singleConnectionDataSource.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
            log.info("The password was changed successfully. User id {}", id);
        } catch (SQLException e) {
            log.error("Failed to update the password. User id {}", id);
            e.printStackTrace();
        }
    }

    /**
     * Deletes the user
     *
     * @param id of the user
     */
    @Override
    public void deleteUser(Long id) {
        String query = String.format("DELETE FROM user WHERE id='%d'", id);

        try (PreparedStatement preparedStatement =
                     singleConnectionDataSource.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
            log.info("User with id {} was removed successfully", id);
        } catch (SQLException e) {
            log.error("Failed to delete user. User id {}", id);
            e.printStackTrace();
        }
    }

    /**
     * Fetches the user from the database
     *
     * @param id of the user
     */
    @Override
    public User getUserById(Long id) {
        String query = String.format("SELECT * FROM user WHERE id=%d", id);

        try (PreparedStatement preparedStatement =
                     singleConnectionDataSource.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
            log.info("User with id {} was fetched successfully", id);
            return user;
        } catch (SQLException e) {
            log.error("Failed to retrieve user with id {}", id);
            e.printStackTrace();
        }
        return null; //TO DO: maybe throw an exception
    }

    @Override
    public keystone.dto.User getUserByEmail(String email) {
        // to be changed to a List<User>
        String query = String.format("SELECT * FROM user WHERE email='%s'", email);
        User user = new User();

        try (PreparedStatement preparedStatement =
                     singleConnectionDataSource.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
            return mapper.map(user, keystone.dto.User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
