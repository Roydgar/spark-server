package tk.roydgar.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NumberFormatException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex){
        return null;
    }

}
