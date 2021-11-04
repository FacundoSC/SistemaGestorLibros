
package com.egg.web.library.controller;

import com.egg.web.library.entity.Author;
import com.egg.web.library.entity.Book;
import com.egg.web.library.entity.Editorial;
import com.egg.web.library.service.AuthorService;
import com.egg.web.library.service.BookService;
import com.egg.web.library.service.EditorialService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    AuthorService aService;
    @Autowired
    EditorialService eService;
    @Autowired
    BookService bService;

    @PostMapping(value="/guardarLibro",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE} )
    public  String   SaveLibro(@RequestBody String  libro)  {
        JSONObject myJson = new JSONObject(libro);
        String  titulo = myJson.get("titulo").toString();
        Long  isbn   = Long.parseLong(myJson.get("isbn").toString());
        int   year = Integer.parseInt(myJson.get("year").toString());
        int ejemplares = Integer.parseInt(myJson.get("ejemplares").toString());
        String idAutor = myJson.get("idAutor").toString();
        String idEditorial = myJson.get("idEditorial").toString();
        Author author = aService.lookForId(idAutor);
        Editorial editorial = eService.lookForId(idEditorial);
        Book book = new Book(isbn,titulo,year,ejemplares,0,0,Boolean.TRUE,author,editorial);
        bService.CreateBook(book);
        myJson = new JSONObject();
        myJson.put("message","se  creo  el  libro  con exito ");
        myJson.put("status","OK");
        return myJson.toString();
    }

    @GetMapping(value="/crearLibro")
    public ModelAndView CrearLibro() {
        ModelAndView mav = new ModelAndView("crearLibro");
        mav.addObject("title", "CrearLibro");
        mav.addObject("autores", aService.findAllActive());
        mav.addObject("editoriales",eService.findAllActive());
        return mav;
    }



    @GetMapping(value="/listarLibros")
    public List<Book> listarLibros() {
        List <Book> libros = bService.findAll();
        return libros;
    }
    @GetMapping("/mostrarLibros")
    public ModelAndView MostrarLibros() {
        ModelAndView mav = new ModelAndView("listarLibros");
        mav.addObject("title", "mostrarLibros");
        mav.addObject("libros", listarLibros());
        return mav;
    }



    @GetMapping("/modificarLibro")
    public ModelAndView modificarLibro(@RequestParam String id) {
        Book book = bService.lookForId(id);
        ModelAndView mav = new ModelAndView("modificarLibro");
        mav.addObject("title", "modificarLibro");
        mav.addObject("libro", book);
        mav.addObject("autores", aService.findAllActive());
        mav.addObject("editoriales",eService.findAllActive());
        return mav;
    }
    @PutMapping(value = "/modificaLibro", consumes = {MediaType.APPLICATION_JSON_VALUE} , produces = {MediaType.APPLICATION_JSON_VALUE})
    public String ModificarLibro(@RequestBody String libro){
        JSONObject myJson = new JSONObject(libro);
        Book book = bService.lookForId(myJson.get("id").toString());
        book.setIsbn(Long.parseLong(myJson.get("isbn").toString()));
        book.setTitle(myJson.get("title").toString());
        book.setYear(Integer.parseInt(myJson.get("year").toString()));
        book.setCopies(Integer.parseInt(myJson.get("copies").toString()));
        book.setAuthor(aService.lookForId(myJson.get("idAutor").toString()));
        book.setEditorial(eService.lookForId(myJson.get("idEditorial").toString()));
        bService.CreateBook(book);
        myJson = new JSONObject();
        myJson.put("message","se  modifico el editorial con exito");
        myJson.put("status","OK");
        return myJson.toString();

    }





    @GetMapping(value="/altaLibro",produces = MediaType.APPLICATION_JSON_VALUE)
    private String AltaLibro(@RequestParam String id)  {
        JSONObject myJson = new JSONObject();
        Book book =bService.lookForId(id);
        book.setStatus(Boolean.TRUE);
        bService.CreateBook(book);
        myJson.put("message","el Libro  ha sido dada de alta");
        myJson.put("status","OK");
        myJson.put("description","alta");
        return myJson.toString();
    }


    @GetMapping(value="/bajaLibro", produces = MediaType.APPLICATION_JSON_VALUE)
    private String BajaLibro(@RequestParam String id) {
        JSONObject myJson = new JSONObject();
         Book book =bService.lookForId(id);
         book.setStatus(Boolean.FALSE);
        bService.CreateBook(book);
        myJson.put("message","El libro  ha sido dada de baja");
        myJson.put("status","OK");
        myJson.put("description","baja");
        return myJson.toString();
    }








}
