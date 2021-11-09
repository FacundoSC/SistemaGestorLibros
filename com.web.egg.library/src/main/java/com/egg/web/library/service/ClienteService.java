package com.egg.web.library.service;


import com.egg.web.library.copies.ClienteCopie;
import com.egg.web.library.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.egg.web.library.entity.Cliente;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listaCliente() {
        List <Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public void CrearCliente(ClienteCopie clienteCopie) {
        Cliente cliente = ClienteCopie.ObtenerCliente(clienteCopie);
        clienteRepository.save(cliente);
    }

    public void changeStatus(int id, Boolean status) {
       Cliente cliente = clienteRepository.getById(id);
       cliente.setEstado(status);
       clienteRepository.save(cliente);
    }
}
