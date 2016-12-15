package keystone.utilities;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DatabaseConnection {
    public static Connection getSqlConnection() {
        Connection connection = null;
        try {
            log.info("Buiding a new sql connection");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://e-asistenta.ro:3306/easisten_keystone?serverTimezone=UTC&useSSL=false",
                    "easisten_keystoneuser",
                    "colorado");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static Connection getSqlConnectionExperimental() {
        Connection connection = null;
        try {
            log.info("Buiding a new sql connection");
            connection = DriverManager.getConnection(
                    DatabaseConnectionUrlBuilder.newConnectionUrl()
                            .vendor("mysql")
                            .host("e-asistenta.ro")
                            .port("3306")
                            .dbName("easisten_keystone")
                            .serverTimezone("UTC")
                            .build(),
                    "easisten_keystoneuser",
                    "colorado");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
