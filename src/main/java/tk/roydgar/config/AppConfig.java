package tk.roydgar.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static tk.roydgar.util.constants.Constants.DEFAULT_PACKAGE;
import static tk.roydgar.util.constants.FilePaths.APPLICATION_PROPERTIES;


@Configuration
@ComponentScan(DEFAULT_PACKAGE)
@PropertySource(APPLICATION_PROPERTIES)
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/workshop?reconnect=true");
        dataSource.setUsername("roydgaryshka");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
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
