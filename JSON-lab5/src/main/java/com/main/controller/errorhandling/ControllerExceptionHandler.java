package com.main.controller.errorhandling;

import com.main.service.exceptions.FileFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({JAXBException.class, IOException.class})
    protected ResponseEntity<Object> handleInputException(Exception e, WebRequest request) {
        return handleExceptionInternal(e, "Internal Server Error",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({FileFormatException.class})
    protected ResponseEntity<Object> handleFileFormatException(FileFormatException e, WebRequest request) {
        return handleExceptionInternal(e, "Unsupported file format",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
