package tk.roydgar.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan("tk.roydgar")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
