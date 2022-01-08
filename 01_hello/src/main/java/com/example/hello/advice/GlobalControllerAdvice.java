package com.example.hello.advice;


import com.example.hello.controller.ExceptionApiController;
import com.example.hello.dto.Error;
import com.example.hello.dto.ErrorResponse;
import com.example.hello.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

//(basePackages = "com.example.hello.controller") 하위 다 잡음
//(basePackageClasses = ExceptionApiController.class) 특정클래스만 적용
@RestControllerAdvice(basePackageClasses = ExceptionApiController.class)
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<Error> errorList = new ArrayList<>();
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(
                    objectError -> {
                        FieldError field =  (FieldError) objectError;
                        String fieldName = field.getField();
                        String message = field.getDefaultMessage();
                        String value = field.getRejectedValue().toString();
//                        System.out.println("------------------");
//                        System.out.println("fieldName = " + fieldName);
//                        System.out.println("message = " + message);
//                        System.out.println("value = " + value);
                        Error errorMessage = new Error();
                        errorMessage.setMessage(message);
                        errorMessage.setField(fieldName);
                        errorMessage.setInvalidValue(value);
                        errorList.add(errorMessage);
                    }
            );
        }
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setResultCode("FAIL");
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(HttpServletRequest httpServletRequest,ConstraintViolationException e){
        List<Error> errorList = new ArrayList<>();
        e.getConstraintViolations().forEach(
                error -> {

                    //Stream을 만들어줌
                    Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
                    List<Path.Node> list = stream.collect(Collectors.toList());

                    String field = list.get(list.size() - 1).getName();
                    String message = error.getMessage();
                    String invalidValue = error.getInvalidValue().toString();

//                    System.out.println("=====================");
//                    System.out.println("field = " + field);
//                    System.out.println("message = " + message);
//                    System.out.println("invalidValue = " + invalidValue);
                    Error errorMessage = new Error();
                    errorMessage.setMessage(message);
                    errorMessage.setField(field);
                    errorMessage.setInvalidValue(invalidValue);
                    errorList.add(errorMessage);
                }
        );
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage(e.getMessage());
        errorResponse.setResultCode("FAIL");
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(HttpServletRequest httpServletRequest,MissingServletRequestParameterException e){
        List<Error> errorList = new ArrayList<>();
        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String message = e.getMessage();
//        System.out.println("fieldName = " + fieldName);
//        System.out.println("fieldType = " + fieldType);
//        System.out.println("message = " + message);
        Error errorMessage = new Error();
        errorMessage.setMessage(e.getMessage());
        errorMessage.setField(fieldName);
        errorList.add(errorMessage);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage(e.getMessage());
        errorResponse.setResultCode("FAIL");
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //Interceptor 에서 사용
    @ExceptionHandler(AuthException.class)
    public ResponseEntity authException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
