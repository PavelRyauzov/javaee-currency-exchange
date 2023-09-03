package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SqliteDataSource implements DataSource {
    private final HikariDataSource dataSource;

    public SqliteDataSource() {
        dataSource = new HikariDataSource(
                new HikariConfig("/db/datasource.properties")
        );
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
