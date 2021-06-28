package com.dcamarero.proyectomvc.dao;

import com.dcamarero.proyectomvc.models.Cart;
import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.models.User;
import com.dcamarero.proyectomvc.models.enums.EstadoType;
import org.springframework.data.repository.CrudRepository;

public interface CartDAO extends CrudRepository<Cart, Long>
{

    Iterable<Cart> findAllByEstado(EstadoType state);

    Iterable<Cart> findAllBySeller(User userDTO);

    Iterable<Cart> findAllByCliente(Cliente cliente);

    Iterable<Cart> findAllByClienteAndEstado(Cliente cliente, EstadoType estadoType);

}
