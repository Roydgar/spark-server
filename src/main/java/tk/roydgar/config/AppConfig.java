package tk.roydgar.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.roydgar.util.SmtpMailSender;
import tk.roydgar.util.StringHasher;

import javax.sql.DataSource;

import java.util.Properties;

import static tk.roydgar.util.constants.Constants.DEFAULT_PACKAGE;
import static tk.roydgar.util.constants.FilePaths.APPLICATION_PROPERTIES;
import static tk.roydgar.util.constants.FilePaths.DATABASE_PROPERTIES;
import static tk.roydgar.util.constants.FilePaths.MAIL_PROPERTIES;


@Configuration
@EnableTransactionManagement
@ComponentScan(DEFAULT_PACKAGE)
@PropertySource(APPLICATION_PROPERTIES)
@PropertySource(DATABASE_PROPERTIES )
@PropertySource(MAIL_PROPERTIES)
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

    @Bean
    public JavaMailSender getJavaMailSender() {
        StringHasher hasher = new StringHasher();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(hasher.decrypt(environment.getProperty("gmail.username")));
        mailSender.setPassword(hasher.decrypt(environment.getProperty("gmail.password")));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SmtpMailSender smtpMailSender(JavaMailSender mailSender) {
        return new SmtpMailSender(mailSender);
    }

    @Bean
    public StringHasher stringHasher() {
        return new StringHasher();
    }

}
