package com.egg.web.library.validation;

import com.egg.web.library.config.GlobalExceptionHandler;

import java.util.Optional;

public class Validation {

    public static void validationService(String name) throws GlobalExceptionHandler.MyExceptionService {
        if (name == null || name.isEmpty()) {
            throw  GlobalExceptionHandler.MyExceptionService.nameAuthor();
        }
    }

    public static void validationIDfound(String id, Optional reponse) throws GlobalExceptionHandler.MyExceptionService {

        if (!reponse.isPresent()) {
            throw  GlobalExceptionHandler.MyExceptionService.idNotFound();
        }

    }
    public static void validationService(Long isbn, String title, Integer year, Integer copies) throws GlobalExceptionHandler.MyExceptionService {

        if (title == null || title.isEmpty()) {
            throw  GlobalExceptionHandler.MyExceptionService.titleBook();
        } if (isbn == null ) {
            throw  GlobalExceptionHandler.MyExceptionService.nameAuthor();
        } if (year == null ) {
            throw  GlobalExceptionHandler.MyExceptionService.isbn();
        } if (copies == null ) {
            throw  GlobalExceptionHandler.MyExceptionService.copies();
        }

    }
}
