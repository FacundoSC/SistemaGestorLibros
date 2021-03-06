package com.egg.web.library.service;

import com.egg.web.library.entity.Editorial;
import com.egg.web.library.config.GlobalExceptionHandler;
import com.egg.web.library.repository.EditorialRepository;
import com.egg.web.library.validation.Validation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRep;

    @Transactional
    public void CrearEditorial(String name) throws GlobalExceptionHandler.MyExceptionService {
        try {
            Validation.validationService(name);
        } catch (GlobalExceptionHandler.MyExceptionService e) {
            throw GlobalExceptionHandler.MyExceptionService.nameAuthor();
        }
        Editorial editorial = new Editorial();
        editorial.setName(name);
        editorial.setStatus(true);
        editorialRep.save(editorial);
    }

    @Transactional
    public void CrearEditorial(Editorial ed){
        editorialRep.save(ed);
    }



    @Transactional
    public void changeState(String id, Boolean status) {
        Editorial editorial = editorialRep.findById(id).get();
        editorial.setStatus(status);
        editorialRep.save(editorial);

    }




//NO ESTOY USANDO MI QUERY ESPECIAL PARA ESTO

    @Transactional
    public void modifyName(String id, String name) throws GlobalExceptionHandler.MyExceptionService {
        try {
            Validation.validationService(name);
            Optional<Editorial> reponse = editorialRep.findById(id);
            Validation.validationIDfound(id, reponse);

        } catch (GlobalExceptionHandler.MyExceptionService e) {
            throw new GlobalExceptionHandler.MyExceptionService();
        }

        Editorial editorial = editorialRep.findById(id).get();
        editorial.setName(name);
        editorialRep.save(editorial);

    }

    @Transactional
    public void unsuscribe(String id) throws GlobalExceptionHandler.MyExceptionService {
        try {
            Optional<Editorial> reponse = editorialRep.findById(id);
            Validation.validationIDfound(id, reponse);

        } catch (GlobalExceptionHandler.MyExceptionService e) {
            throw new GlobalExceptionHandler.MyExceptionService();
        }

        Editorial editorial = editorialRep.findById(id).get();
        editorial.setStatus(false);
        editorialRep.save(editorial);
    }

    @Transactional(readOnly = true)
    public Editorial lookForId(String id) {
        Optional<Editorial> editorialOptional = editorialRep.findById(id);
        return editorialOptional.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Editorial> findAll() {
        return editorialRep.findAll();
    }

    @Transactional(readOnly = true)
    public List<Editorial> obtenerEditoriales() {
        return editorialRep.listarEditoriales();
    }

    @Transactional(readOnly = false)
    public String activarEditorial(String id) {
       return editorialRep.activarEditorial(id);
    }

    @Transactional(readOnly = true)
    public List<Editorial> findAllActive() {
        return editorialRep.findAll().stream().filter(editorial -> (editorial.isStatus() == Boolean.TRUE)).collect(Collectors.toList());
    }



    @Transactional(readOnly = false)
    public String desactivarEditorial(String id) {
        return editorialRep.desactivarEditorial(id);
    }



}
