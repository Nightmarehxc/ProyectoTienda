package com.dcamarero.proyectomvc.services.impl;


import com.dcamarero.proyectomvc.dao.UserDao;
import com.dcamarero.proyectomvc.exception.EntityNotFoundException;
import com.dcamarero.proyectomvc.models.User;
import com.dcamarero.proyectomvc.services.UserService;
import com.dcamarero.proyectomvc.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The type User service.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService
{

    private final UserDao userDao;


    /**
     * Instantiates a new User service.
     *
     * @param userDao the user dao
     */
    @Autowired
    public UserServiceImpl(final UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public boolean checkPassword(final String username, final String password)
    {

        return true;
    }

    @Override
    public void save(final User user)
    {
        try
        {
            userDao.save(user);

        }
        catch (EntityExistsException e)
        {
            userDao.save(user);
        }

    }

    @Override
    public void saveNewUser(final User user)
    {
        try
        {
            //codifica la pass
            user.setPassword(UserUtils.hashPassword(user.getPassword()));
            userDao.save(user);
//            log.info("new user Saved");
        }
        catch (EntityExistsException e)
        {
            userDao.save(user);
        }

    }

    @Override
    public void delete(final User user)
    {
        userDao.delete(user);
    }

    @Override
    public User getUserById(final Long id)
    {
        final Optional<User> response = userDao.findById(id);
        return response.orElse(null);
    }

    @Override
    public User getUserByDni(final String dni) throws EntityNotFoundException
    {
        final Optional<User> response = userDao.findByDni(dni);

        if (response.isPresent())
            return response.get();

        throw new EntityNotFoundException("User not found on database");
    }

    @Override
    public User getUserByEmail(final String email) throws EntityNotFoundException
    {
        final Optional<User> response = userDao.findByEmail(email);

        if (response.isPresent())
            return response.get();

        throw new EntityNotFoundException("User not found on database");
    }

    @Override
    public User getUserByName(final String name)
    {
        final Optional<User> response = userDao.findByName(name);
        return response.orElse(null);
    }

    @Override
    public Set<User> getAllUsers()
    {
        final Set<User> result = new HashSet<>();
        userDao.findAll().forEach(result::add);
        return result;
    }

    @Override
    public void updateLoginDate(User userDTO)
    {
        userDTO.setLastLogin(LocalDateTime.now());
        userDao.save(userDTO);
    }


}
