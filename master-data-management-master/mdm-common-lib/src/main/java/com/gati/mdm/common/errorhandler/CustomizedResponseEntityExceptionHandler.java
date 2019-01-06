package com.gati.mdm.common.errorhandler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gati.mdm.common.error.ErrorDetails;
import com.gati.mdm.common.exception.business.ResourceAlreadyFoundException;
import com.gati.mdm.common.exception.business.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    // The name of below variable is intensionally kept as HANDLERLOGGER to avoid clash with superclass
    // defined logger variable.
    public static final Logger HANDLERLOGGER = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        HANDLERLOGGER.debug("Entering handleAllExceptions with request:{}", request);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAlreadyFoundException.class)
    public final ResponseEntity<Object> handleResourceAlreadyFoundException(ResourceAlreadyFoundException ex,
            WebRequest request) {
        HANDLERLOGGER.debug("Entering handleResourceAlreadyFoundException with request:{}", request);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,
            WebRequest request) {
        HANDLERLOGGER.debug("Entering handleResourceNotFoundException with request:{}", request);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        HANDLERLOGGER.debug("Entering handleMethodArgumentNotValid with request:{}", request);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Validation Failed", ex.getBindingResult()
                                                                                                 .toString());
        return new ResponseEntity(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }
}
