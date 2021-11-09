package com.egg.web.library.service;

import com.egg.web.library.entity.Author;
import com.egg.web.library.config.GlobalExceptionHandler;
import com.egg.web.library.repository.AuthorRepository;
import com.egg.web.library.validation.Validation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {
//    consulta, creación, modificación y dar de baja

    @Autowired
    private AuthorRepository authorRep;



    @Transactional
    public void createAuthor(String name) throws GlobalExceptionHandler.MyExceptionService {
        try {
            Validation.validationService(name);
        } catch (GlobalExceptionHandler.MyExceptionService e) {
            throw GlobalExceptionHandler.MyExceptionService.nameAuthor();
        }

        Author author = new Author();
        author.setName(name);
        author.setStatus(true);
        authorRep.save(author);
    }
    @Transactional
    public void crearAuthor(Author au){
        authorRep.save(au);
    }

    @Transactional
    public void modificarAuthor(Author author){
        authorRep.modifyName(author.getId(), author.getName());
    }

    @Transactional
    public void modifyName(String id, String name) throws GlobalExceptionHandler.MyExceptionService {
        try {
            Validation.validationService(name);
            Optional<Author> reponse = authorRep.findById(id);
            Validation.validationIDfound(id, reponse);

        } catch (GlobalExceptionHandler.MyExceptionService e) {
            throw new GlobalExceptionHandler.MyExceptionService();
        }

        Author author = authorRep.findById(id).get();
        author.setName(name);
        authorRep.save(author);

    }

    @Transactional
    public void changeState(String id, Boolean status) throws GlobalExceptionHandler.MyExceptionService {
        try {
            Optional<Author> reponse = authorRep.findById(id);
            Validation.validationIDfound(id, reponse);

        } catch (GlobalExceptionHandler.MyExceptionService e) {
            throw new GlobalExceptionHandler.MyExceptionService();
        }
     
        Author author = authorRep.findById(id).get();
        author.setStatus(status);
        authorRep.save(author);
    }

    @Transactional(readOnly = true)
    public Author lookForId(String id) {
        Optional<Author> authorOptional = authorRep.findById(id);
        return authorOptional.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRep.findAll();
    }

    @Transactional(readOnly = true)
    public List<Author> findAllActive() {
        return authorRep.findAll().stream().filter(autor -> (autor.isStatus()== Boolean.TRUE)).collect(Collectors.toList());
    }

//   Nose como buscar por nombre !  
    //@Transactional(readOnly = true)
//    public Author findByName(String name) {
//
//        return authorOptional.orElse(null);
//    }
}
