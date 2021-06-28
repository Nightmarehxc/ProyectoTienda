package com.dcamarero.proyectomvc.services;

import com.dcamarero.proyectomvc.models.Cart;
import com.dcamarero.proyectomvc.models.Cliente;

import java.util.Set;

public interface SalesService
{

    Set<Cart> checkEditando(Cliente clienteDTO);

    Cart createCart(Cliente client);

    Cart createNewCart(Cliente client);

    Cart updateCart(String clientID, int cant, long itemID);
}
