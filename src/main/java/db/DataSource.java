package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final HikariConfig config = new HikariConfig("/db/datasource.properties");
    private static final HikariDataSource dataSource = new HikariDataSource(config);

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
