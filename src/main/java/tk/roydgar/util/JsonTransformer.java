package tk.roydgar.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String render(Object model) {
        return mapper.writeValueAsString(model);
    }

}