package config;


import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {

    private Properties properties;

    private static DatabaseConfig databaseConfig;

    private DriverManagerDataSource dataSource;

    private DatabaseConfig(){
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            System.err.println("Database.properties is not find!");
        }
    }

    public static DatabaseConfig getInstance(){
        if(databaseConfig != null) return databaseConfig;

        return databaseConfig = new DatabaseConfig();
    }

    public DriverManagerDataSource getDataSource(){
        if(dataSource != null) return dataSource;

        dataSource = new DriverManagerDataSource();
        dataSource.setUrl(properties.getProperty("datasource.url"));
        dataSource.setUsername(properties.getProperty("datasource.username"));
        dataSource.setPassword(properties.getProperty("datasource.password"));
        dataSource.setDriverClassName(properties.getProperty("datasource.driver-class-name"));

        return dataSource;
    }
}
