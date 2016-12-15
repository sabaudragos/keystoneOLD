package keystone.dao;

import keystone.domain.User;
import keystone.utilities.DatabaseConnection;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class UserDaoImpl implements UserDao {
    private Connection connection =
            DatabaseConnection.getSqlConnection();

    public static void main(String[] args) {
        User user = new User();
        user.setName("DragosFive");
        user.setEmail("dragosFive@codeark.org");
        user.setPassword("hahaha");

        UserDaoImpl userDao = new UserDaoImpl();
        userDao.createUser(user);

    }

    @Override
    public void createUser(User user) {
        String query = String.format("INSERT INTO user (name,email,password) VALUES('%s','%s','%s')",
                user.getName(), user.getEmail(), user.getPassword());

        Statement statement;

        try {
            statement = connection.prepareStatement(query);
            int noOfRowsAffected = statement.executeUpdate(query);
            log.info("{} rows were affected", noOfRowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
