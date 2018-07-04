package tk.roydgar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import tk.roydgar.util.constants.FilePaths;

import javax.annotation.Resource;

@Configuration
@ComponentScan("tk.roydgar")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
