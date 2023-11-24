package az.spring.controller;

import az.spring.exception.ResourceNotFoundException;
import az.spring.exception.enums.EnumCode;
import az.spring.rest.model.response.ErrorResponse;
import jakarta.servlet.annotation.ServletSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.invoke.WrongMethodTypeException;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException exception) {

        return ErrorResponse.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleWrongTypeInput(MethodArgumentTypeMismatchException e) {

        return ErrorResponse.builder()
                .code(EnumCode.VALIDATION_ERROR.getId())
                .message(e.getMessage())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleWrongTypeInsert(MethodArgumentNotValidException e){

        return ErrorResponse.builder()
                .code(EnumCode.WRONG_TYPE_INSERT.getId())
                .message(EnumCode.WRONG_TYPE_INSERT.getMessage())
                .build();
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAllExceptions(Exception e){
         return ErrorResponse.builder()
                 .code(EnumCode.UNKNOWN_ERROR.getId())
                 .message(EnumCode.UNKNOWN_ERROR.getMessage())
                 .build();

    }

}
