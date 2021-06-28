package com.dcamarero.proyectomvc.services;

import com.dcamarero.proyectomvc.models.Cliente;

import java.util.Set;

public interface ClienteService
{
    void save(Cliente clienteDTO);

    void saveNewUser(Cliente clienteDTO);

    Cliente getClienteByDNI(String dni);

    Cliente getClienteByEmail(String email);

    void updateLoginDate(Cliente userDTO);

    Set<Cliente> getAllClients();
}
