package tk.roydgar.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tk.roydgar.util.HttpHeadersUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;

import static tk.roydgar.util.HttpHeadersUtil.httpHeaders;
import static tk.roydgar.util.constants.HeaderMessages.ERROR;
import static tk.roydgar.util.constants.HeaderMessages.INTERNAL_ERROR;
import static tk.roydgar.util.constants.HeaderMessages.INVALID_ID;

@ControllerAdvice
public class ExceptionHandlerController {

    @Resource
    private Logger logger;

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberCastException(HttpServletRequest request, Exception ex){
        logger.error(ERROR, ex);
        HttpHeaders httpHeaders = httpHeaders(ERROR, INVALID_ID);
        return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleGeneralException(HttpServletRequest request, Exception ex) {
        logger.error(ERROR, ex);
        HttpHeaders httpHeaders = httpHeaders(ERROR, INTERNAL_ERROR);
        return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationException(HttpServletRequest request, Exception ex) {
        logger.error(ERROR, ex);
        HttpHeaders httpHeaders = httpHeaders(ERROR, ex.getMessage());
        return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
    }

}
