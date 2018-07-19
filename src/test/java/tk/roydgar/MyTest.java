package tk.roydgar;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.roydgar.model.entity.user.Car;
import tk.roydgar.model.entity.user.User;
import tk.roydgar.util.HashUtil;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


//@SpringBootTest
//@RunWith(SpringRunner.class)
public class MyTest {

    //@Autowired
    //private ObjectMapper jacksonObjectMapper;

    @Test
    public void test() throws Exception {
        sendRegisterRequest();
    }

    private static void sendRegisterRequest() {
    }


}