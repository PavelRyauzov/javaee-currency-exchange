package db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

public class SqliteDataSource implements DataSource {
    private final HikariDataSource dataSource;

    public SqliteDataSource() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl(String.format("jdbc:sqlite:%s", getSqliteDbPath("db/currencyexchange.db")));
        config.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        config.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        config.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    private String getSqliteDbPath(String dbFileName) throws Exception {
        URL resourceUrl = this.getClass().getClassLoader().getResource(dbFileName);

        if (resourceUrl == null) {
            throw new IllegalArgumentException("Sqlite database file not found: " + dbFileName);
        }

        try {
            return new File(resourceUrl.toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new Exception(e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
