package com.gsteren.capitolechallenge.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.gsteren.capitolechallenge.dto.ErrorDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerErrorAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleAll(Exception exception, WebRequest request) {
        return getErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        
    	return getErrorDTO(HttpStatus.BAD_REQUEST.value(),exception.getFieldError().getDefaultMessage());
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodNotSupportedException(Exception exception, WebRequest request) {
        return getErrorDTO(HttpStatus.BAD_REQUEST.value(),exception);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNoSuchElementException(NoSuchElementException exception) {
        return getErrorDTO(HttpStatus.NOT_FOUND.value(),exception);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        String message ="BAD_REQUEST";
        if (exception.getMessage().contains("JSON parse error")) {
            message = "INVALID_JSON";
        }
        return getErrorDTO(HttpStatus.BAD_REQUEST.value(),
                message);
    }


    private ErrorDTO getErrorDTO(int errorCode, Exception exception) {
        log.error("Exception While Processing Request ", exception);
        ErrorDTO errorDTO = new ErrorDTO();
        ErrorDTO.ErrorEntry errorEntry1 = new ErrorDTO.ErrorEntry(
                LocalDateTime.now(), 
                errorCode,
                exception.getMessage()
        );
        List<ErrorDTO.ErrorEntry> errorList = new ArrayList<>();
        errorList.add(errorEntry1);
        
        errorDTO.setError(errorList);
        return errorDTO;
    }
    
    private ErrorDTO getErrorDTO(int errorCode, String errorMessage) {
        log.error("Exception While Processing Request ", errorMessage);
        ErrorDTO errorDTO = new ErrorDTO();
        ErrorDTO.ErrorEntry errorEntry1 = new ErrorDTO.ErrorEntry(
                LocalDateTime.now(), 
                errorCode,
                errorMessage
        );
        List<ErrorDTO.ErrorEntry> errorList = new ArrayList<>();
        errorList.add(errorEntry1);
        
        errorDTO.setError(errorList);
        return errorDTO;
    }

}
