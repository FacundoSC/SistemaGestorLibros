package com.egg.web.library.config;


import com.egg.web.library.exception.ErrorInfo;
import com.egg.web.library.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {


     @ExceptionHandler({NumberFormatException.class,MissingServletRequestParameterException.class})
     public  ResponseEntity<?> BadRequestException(HttpServletRequest request ,Exception e){
         String message = obtenerMessage(e.getMessage());
         ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message, request.getRequestURI(), new Date());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
     }

    private String obtenerMessage(String message) {
         String respuesta="";
        if (message.contains("NumberFormatException")){
            respuesta = "Error: no es posible  formatear el valor del parametro a un numero";
        }
        else if(message.contains("Required request parameter 'id' for method parameter type int is not present")){
            respuesta = "el parametro id es obligatorio";
        }
        else{
            respuesta = message;
        }
        return respuesta;
    }

    @ExceptionHandler({NotFoundException.class})
     public ResponseEntity<?> NotFoundException(HttpServletRequest request ,Exception e){
         ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(),e.getMessage(), request.getRequestURI(), new Date());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);

     }










    public static class MyExceptionService extends Exception {

        public MyExceptionService() {
        }

        public MyExceptionService(String ms) {
            super(ms);
        }

        public static MyExceptionService nameAuthor() {

            return new MyExceptionService("El nombre del autor es obligatorio");
        }

        public static MyExceptionService nameEditorial() {

            return new MyExceptionService("El nombre de la editorial es obligatorio");
        }

        public static MyExceptionService idNotFound() {

            return new MyExceptionService("El id ingresado no se encontro");

        }

        public static MyExceptionService titleBook() {

            return new MyExceptionService("El titulo del libro es obligatorio");
        }

        public static MyExceptionService isbn() {

            return new MyExceptionService("El isbn del libro es obligatorio");
        }

        public static MyExceptionService copies() {

            return new MyExceptionService("La cantidad de copias del libro es obligatorio");
        }

    }


}
