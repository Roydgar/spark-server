package tk.roydgar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import spark.template.velocity.VelocityTemplateEngine;
import tk.roydgar.util.JsonTransformer;

import javax.sql.DataSource;


@Configuration
@ComponentScan("tk.roydgar")
public class AppConfig {

    @Bean
    public VelocityTemplateEngine velocityTemplateEngine() {
        return new VelocityTemplateEngine();
    }

    @Bean
    public JsonTransformer jsonTransformer() {
        return new JsonTransformer();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/heroku_d439c41c7bfdff8?reconnect=true");
        dataSource.setUsername("b069c397021c65");
        dataSource.setPassword("bc083c0b");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        return dataSource;
    }

    @Bean(name = "objectMapperDeserializer")
    public ObjectMapper objectMapperDeserializer() {
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

}
