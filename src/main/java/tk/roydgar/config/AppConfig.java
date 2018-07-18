package tk.roydgar.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import tk.roydgar.util.DbPasswordHasher;

import javax.sql.DataSource;

import java.util.logging.LogManager;

import static tk.roydgar.util.constants.Constants.DEFAULT_PACKAGE;
import static tk.roydgar.util.constants.FilePaths.APPLICATION_PROPERTIES;
import static tk.roydgar.util.constants.FilePaths.DATABASE_PROPERTIES;


@Configuration
@ComponentScan(DEFAULT_PACKAGE)
@PropertySource(APPLICATION_PROPERTIES)
@PropertySource(DATABASE_PROPERTIES )
public class AppConfig {

    private Environment environment;

    @Autowired
    public AppConfig(Environment env) {
        this.environment = env;
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("application");
    }

    @Bean
    public DataSource dataSource() {
        DbPasswordHasher hasher = new DbPasswordHasher();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:mysql://eu-cdbr-west-02.cleardb.net/heroku_3d8b1b1abd576b1?reconnect=true");
        dataSource.setUsername("bfae3b2b786739");
        dataSource.setPassword("97063c3d");
        dataSource.setDriverClassName(hasher.decrypt(environment.getProperty("driver")));
        return dataSource;
    }

    @Bean
    public Module parameterNamesModule() {
        return new ParameterNamesModule();
    }

    @Bean
    public Module jdk8Module() {
        return new Jdk8Module();
    }

    @Bean
    public Module javaTimeModule() {
        return new JavaTimeModule();
    }

}
