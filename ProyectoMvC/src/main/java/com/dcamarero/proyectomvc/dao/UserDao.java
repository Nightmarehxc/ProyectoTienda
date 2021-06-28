package com.dcamarero.proyectomvc.dao;

import com.dcamarero.proyectomvc.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User dao.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long>
{
    @Override
    Optional<User> findById(Long aLong);

    /**
     * Find by dni optional.
     *
     * @param dni the dni
     * @return the optional
     */
    Optional<User> findByDni(String dni);

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    @Override
    Iterable<User> findAll();

    @Override
    void delete(User user);

    @Override
    <S extends User> S save(S s);
}
