package tk.roydgar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tk.roydgar.config.AppConfig;
import tk.roydgar.controller.WebController;

import static spark.Spark.initExceptionHandler;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import static tk.roydgar.util.constants.Constants.DEFAULT_PORT;
import static tk.roydgar.util.constants.Constants.STATIC_FILE_LOCATION;

@SpringBootApplication
public class SparkServerApplication {

    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        staticFileLocation(STATIC_FILE_LOCATION);
        port(getAssignedPort());

        initExceptionHandler((e) -> {
            e.printStackTrace();
            logger.error(e);
        });

        context.getBean(WebController.class).setupRoutes();
	}

    private static int getAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return DEFAULT_PORT; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
