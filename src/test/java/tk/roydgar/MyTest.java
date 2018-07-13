package tk.roydgar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyTest {

    @JsonFormat(pattern = "yyyy MM dd HH:mm")
    LocalDateTime time;

    String string = "{\n" +
            "  \"time\" : {\n" +
            "    \"year\" : 2018,\n" +
            "    \"hour\" : 11,\n" +
            "    \"dayOfMonth\" : 26,\n" +
            "    \"minute\" : 0,\n" +
            "    \"monthValue\" : 7\n" +
            "  }\n" +
            "}";
    @Test
    public void test() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule()); // new module, NOT JSR310Module
        final String date;
        try {
            date = mapper.writeValueAsString(LocalDateTime.now());
            System.out.println(date);
            System.out.println(mapper.readValue("[2018, 7, 13, 21, 54]", LocalDateTime.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


