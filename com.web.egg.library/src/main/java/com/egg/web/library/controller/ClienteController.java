package com.egg.web.library.controller;

import antlr.StringUtils;
import com.egg.web.library.copies.ClienteCopie;
import com.egg.web.library.entity.Cliente;
import com.egg.web.library.exception.NotFoundException;
import com.egg.web.library.exception.SuccessInfo;
import com.egg.web.library.service.ClienteService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    ClienteService cService;

    @GetMapping("/listarCliente")
    private List<Cliente> listaCliente() {
        return cService.listaCliente();
    }



    @PostMapping(value="/crearCliente",produces = {MediaType.APPLICATION_JSON_VALUE})
    public  String  CrearCliente(@RequestBody String clienteJson)  {
        JSONObject clienteJsonObject = new JSONObject(clienteJson);
        ClienteCopie clienteCopie = new ClienteCopie();
        clienteCopie.setApellido(clienteJsonObject.getString("apellido"));
        clienteCopie.setNombre(clienteJsonObject.getString("nombre"));
        clienteCopie.setDocumento(clienteJsonObject.getLong("documento"));
        clienteCopie.setTelefono(clienteJsonObject.getString("telefono"));
        clienteCopie.setEstado(clienteJsonObject.getBoolean("estado"));
        cService.CrearCliente(clienteCopie);
        clienteJsonObject = new JSONObject();
        clienteJsonObject.put("message","se  creo  el cliente");
        clienteJsonObject.put("status","OK");
        return clienteJsonObject.toString();
    }

    @PutMapping(value = "/modificarCliente", consumes = {MediaType.APPLICATION_JSON_VALUE} , produces = {MediaType.APPLICATION_JSON_VALUE})
    public String ModificarCliente(@RequestBody String clienteJson){
        JSONObject clienteJsonObject = new JSONObject(clienteJson);
        ClienteCopie clienteCopie = new ClienteCopie();
        clienteCopie.setApellido(clienteJsonObject.getString("apellido"));
        clienteCopie.setNombre(clienteJsonObject.getString("nombre"));
        clienteCopie.setDocumento(clienteJsonObject.getLong("documento"));
        clienteCopie.setTelefono(clienteJsonObject.getString("telefono"));
        clienteCopie.setIdCliente(clienteJsonObject.getInt("id"));
        cService.CrearCliente(clienteCopie);
        clienteJsonObject = new JSONObject();
        clienteJsonObject.put("message","se  modifico el cliente con exito");
        clienteJsonObject.put("status","OK");
        return clienteJsonObject.toString();

    }

    @GetMapping("/desactivarCliente")
    private String desactivarCliente(@RequestParam int id){
         cService.changeStatus(id,Boolean.FALSE);
        String mensaje = "El cliente ha sido desactivado";
        final String json = "{\"mensaje\":\" "+mensaje+" \",\"status\":\""+"OK"+"\"}";
        return json;
    }

    @GetMapping("/activarCliente")
    private ResponseEntity<SuccessInfo> activarCliente(@RequestParam(required = true) int id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        try{
            cService.changeStatus(id,Boolean.TRUE);
        }
        catch(EntityNotFoundException e){
           throw new NotFoundException("no se encuentra el cliente con el id "+id+" en el sistema");
        }
        SuccessInfo responseBodyJson = new SuccessInfo(HttpStatus.OK.value(),"el cliente ha sido activado","/activarCliente",new Date());
        return  ResponseEntity.status(HttpStatus.OK).headers(headers).body(responseBodyJson);
    }



}





