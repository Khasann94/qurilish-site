package com.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@SuppressWarnings({"unchecked", "rawtypes"})
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        if (ex.getMessage().equals("Full authentication is required to access this resource") || ex.getMessage().equals("Full authentication is required to access this resource") || ex.getMessage().equals("Доступ запрещен"))
            return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
        else if (ex.getMessage().equals("Access Denied") || ex.getMessage().equals("Access Denied") || ex.getMessage().equals("Доступ запрещен"))
            return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
        else return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(new Date(), ex.getLocalizedMessage(), "Record Not Found");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There was an error processing the request body.")
    public void handleMessageNotReadableException(HttpServletRequest request, Throwable exception) {
        System.out.println(exception.getMessage());
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return createHttpResponse(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> createHttpResponse(HttpStatus httpStatus) {
        ErrorDetails errorResponse = new ErrorDetails(new Date(), httpStatus.toString(), "Path not found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}