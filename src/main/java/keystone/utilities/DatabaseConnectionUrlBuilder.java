package keystone.utilities;

public class DatabaseConnectionUrlBuilder {
    private String databaseConnectionUrl = "jdbc:";

    public static DatabaseConnectionUrlBuilder newConnectionUrl() {
        return new DatabaseConnectionUrlBuilder();
    }

    public DatabaseConnectionUrlBuilder vendor(String vendor) {
        this.databaseConnectionUrl.concat(vendor + "://");
        return this;
    }

    public DatabaseConnectionUrlBuilder host(String host) {
        this.databaseConnectionUrl.concat(host);
        return this;
    }

    public DatabaseConnectionUrlBuilder port(String port) {
        this.databaseConnectionUrl.concat(":" + port + "/");
        return this;
    }

    public DatabaseConnectionUrlBuilder dbName(String name) {
        this.databaseConnectionUrl.concat(name);
        return this;
    }

    public DatabaseConnectionUrlBuilder serverTimezone(String timeZone) {
        this.databaseConnectionUrl.concat("?serverTimezone=" + timeZone);
        return this;
    }

    public String build() {
        return this.databaseConnectionUrl;
    }
}
