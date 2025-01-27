package core.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import static core.configs.ConfigurationManager.config;

import javax.sql.DataSource;

public class DataBasePoolFactory {
    private static HikariDataSource ds;

    private DataBasePoolFactory(){};

    public static synchronized HikariDataSource getDataSource() {
        if (ds==null){
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(config().dbJdbcUrl());
            config.setUsername(config().dbUsername());
            config.setPassword(config().dbPassword());
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setConnectionTimeout(5000);
            config.setMaxLifetime(1800000);
            config.setPoolName("MyDbPool");
            ds = new HikariDataSource(config);
        }
        return ds;
    }

    public static synchronized void closeDataSource() {
        if (ds != null) {
            ds.close();
        }
    }
}
