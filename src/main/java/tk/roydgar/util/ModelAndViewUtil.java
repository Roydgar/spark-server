package tk.roydgar.util;

import spark.ModelAndView;
import tk.roydgar.util.constants.ParameterNames;
import tk.roydgar.util.constants.TemplatePaths;

import java.util.HashMap;
import java.util.Map;

public class ModelAndViewUtil {

    private ModelAndViewUtil() {}

    public static ModelAndView createModelAndView(String path) {
        Map<String, String> model = new HashMap<>();
        model.put(ParameterNames.TEMPLATE, TemplatePaths.LOGIN_FORM);

        return new ModelAndView(model, TemplatePaths.LAYOUT);
    }

    public static ModelAndView createModelAndView(String path, String error) {
        Map<String, String> model = new HashMap<>();
        model.put(ParameterNames.ERROR_MESSAGE, error);
        model.put(ParameterNames.TEMPLATE, TemplatePaths.LOGIN_FORM);

        return new ModelAndView(model, TemplatePaths.LAYOUT);
    }
}
