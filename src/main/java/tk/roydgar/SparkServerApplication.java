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
        sendLoginRequest();
    }


    private static void sendRegisterRequest() {
        sendRequest("http://localhost:8080/user/register",
                "{\"name\":\"Vitysha\",\"surname\":\"Pups\",\"phone\":\"123213123\", \"password\":\"0000\", \"email\":\"roydgaryshka@gmail.com\",\"cars\":[{\"brand\":\"sfasf\",\"model\":\"asasf\"}]}");
    }

    private static void sendLoginRequest() {
        sendRequest("http://localhost:8080/user/login",
                "{\"email\":\"roydgaryshk3a@gmail.com\", \"password\":\"0000\"}");
    }
    private static void sendRequest(String myUrl, String json) {
        try {
            URL url = new URL(myUrl);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);


            //byte[] out = "{\"time\":[2018, 7, 18, 12, 0], \"customer\": { \"phone\" : \"239892937r8\",  \"name\" : \"Uebashka\",  \"surname\" : \"678\", \"email\" : \"fee@MAIL.com\"}}".getBytes(StandardCharsets.UTF_8);
            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (Exception e) {

        }
    }

}
