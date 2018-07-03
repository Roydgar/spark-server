package tk.roydgar.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tk.roydgar.util.constants.ParameterNames;
import tk.roydgar.util.constants.TemplatePaths;

import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class IndexPageController {

    public TemplateViewRoute handle = (Request request, Response response) -> {
        Map<String, String> model = new HashMap<>();
        model.put(ParameterNames.TEMPLATE, TemplatePaths.INDEX);
        return new ModelAndView(model, TemplatePaths.LAYOUT);
    };

}
