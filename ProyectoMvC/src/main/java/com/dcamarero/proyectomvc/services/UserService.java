package com.dcamarero.proyectomvc.services;

import com.dcamarero.proyectomvc.exception.EntityNotFoundException;
import com.dcamarero.proyectomvc.models.User;

import java.util.Set;

/**
 * The interface User service.
 */
public interface UserService
{
    /**
     * Check password boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    boolean checkPassword(final String username, final String password);

    /**
     * Save.
     *
     * @param user the user
     */
    void save(User user);

    void saveNewUser(User user);

    /**
     * Delete item user model.
     *
     * @param user the user
     */
    void delete(User user);

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    User getUserById(Long id);

    /**
     * Gets user by dni.
     *
     * @param dni the dni
     * @return the user by dni
     * @throws EntityNotFoundException the entity not found exception
     */
    User getUserByDni(String dni) throws EntityNotFoundException;

    User getUserByEmail(String email) throws EntityNotFoundException;

    /**
     * Gets user by name.
     *
     * @param name the username
     * @return the user by name
     */
    User getUserByName(String name);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    Set<User> getAllUsers();

    void updateLoginDate(User userDTO);
}
