package tk.roydgar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SparkServerApplication {

    public static void main(String[] args){
        SpringApplication.run(SparkServerApplication.class, args);
    }

}
