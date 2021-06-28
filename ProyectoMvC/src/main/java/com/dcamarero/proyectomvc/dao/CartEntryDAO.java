package com.dcamarero.proyectomvc.dao;

import com.dcamarero.proyectomvc.models.Cart;
import com.dcamarero.proyectomvc.models.CartEntry;
import org.springframework.data.repository.CrudRepository;

public interface CartEntryDAO extends CrudRepository<CartEntry, Long>
{
    Iterable<CartEntry> findAllByCart(Cart cart);
}
