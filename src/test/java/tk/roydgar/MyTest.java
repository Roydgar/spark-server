package tk.roydgar;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.roydgar.util.HashUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    public void test()throws Exception {
        System.out.println(HashUtil.hash("Roydgaryshka@gmail.com" + "0000"));
    }

}