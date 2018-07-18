package tk.roydgar;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tk.roydgar.model.entity.User;
import tk.roydgar.model.entity.embeddable.Car;
import tk.roydgar.util.SmtpMailSender;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    public void test()throws Exception {
        User user = User.builder().email("roydgaryshka@gmail.com")
                .name("vit").surname("pups").phone("22299").password("1233")
                .car(new Car("saf", "sfa")).build();
        System.out.println(jacksonObjectMapper.writeValueAsString(user));
    }

}