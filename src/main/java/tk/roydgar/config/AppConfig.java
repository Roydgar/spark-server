package tk.roydgar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import spark.template.velocity.VelocityTemplateEngine;
import tk.roydgar.util.JsonTransformer;


@Configuration
@ComponentScan("tk.roydgar")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public VelocityTemplateEngine velocityTemplateEngine() {
        return new VelocityTemplateEngine();
    }

    @Bean
    public JsonTransformer jsonTransformer() {
        return new JsonTransformer();
    }

}
