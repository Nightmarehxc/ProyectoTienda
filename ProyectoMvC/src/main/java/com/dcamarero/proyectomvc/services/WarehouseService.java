package com.dcamarero.proyectomvc.services;

import com.dcamarero.proyectomvc.models.Cart;
import com.dcamarero.proyectomvc.models.CartEntry;
import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.models.enums.EstadoType;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

public interface WarehouseService
{
    Set<Cart> getAllCartsByClient(Cliente client);

    Set<Cart> getAllCartsByUser(String dni);

    Set<Cart> getAllCartsByState(int estado) throws EntityNotFoundException;

    Cart getCart(long id);

    Cart updateCart(long id, int state, String userMod);

    Set<CartEntry> getAllCartEntriesByCart(Cart cart);

    Set<Cart> getCartByClientANDEstado(EstadoType estadoType, Cliente c);
}
