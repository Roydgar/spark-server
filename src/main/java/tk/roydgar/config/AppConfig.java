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
import tk.roydgar.util.StringHasher;

import javax.sql.DataSource;

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
        StringHasher hasher = new StringHasher();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(hasher.decrypt(environment.getProperty("heroku.url")));
        dataSource.setUsername(hasher.decrypt(environment.getProperty("heroku.username")));
        dataSource.setPassword(hasher.decrypt(environment.getProperty("heroku.password")));
        dataSource.setDriverClassName(hasher.decrypt(environment.getProperty("driver")));
        return dataSource;
    }


}
