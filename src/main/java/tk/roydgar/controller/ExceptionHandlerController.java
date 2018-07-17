package tk.roydgar.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @Resource
    private Logger logger;

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberCastException(HttpServletRequest request, Exception ex){
        logger.error("Bad request [400]", ex);
        return null;
    }

    @ExceptionHandler(Throwable.class)
    public String handleGeneralException(HttpServletRequest request, Exception ex) {
        logger.error("Exception", ex);
        return null;
    }

}
