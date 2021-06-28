package com.dcamarero.proyectomvc.dao;

import com.dcamarero.proyectomvc.models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDAO extends CrudRepository<Cliente, Long>
{


    @Override
    <S extends Cliente> S save(S s);

    @Override
    <S extends Cliente> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Cliente> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<Cliente> findAll();

    @Override
    Iterable<Cliente> findAllById(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Cliente employ);

    @Override
    void deleteAllById(Iterable<? extends Long> iterable);

    @Override
    void deleteAll(Iterable<? extends Cliente> iterable);

    @Override
    void deleteAll();

    Optional<Cliente> findByDni(String dni);

    Iterable<Cliente> findByName(String name);

    Optional<Cliente> findByEmail(String email);

}
