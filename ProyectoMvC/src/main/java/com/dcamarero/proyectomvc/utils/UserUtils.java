package com.dcamarero.proyectomvc.utils;

import com.dcamarero.proyectomvc.models.Cliente;
import com.dcamarero.proyectomvc.models.User;
import org.mindrot.jbcrypt.BCrypt;

public class UserUtils
{
    public static String hashPassword(final String plainTextPassword)
    {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean comparePass(final String hash, User user)
    {
        return BCrypt.checkpw(hash, user.getPassword());
    }

    public static boolean comparePass(final String hash, Cliente user)
    {
        return BCrypt.checkpw(hash, user.getPassword());
    }


}
