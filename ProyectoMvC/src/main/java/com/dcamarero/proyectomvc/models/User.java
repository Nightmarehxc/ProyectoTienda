package com.dcamarero.proyectomvc.models;

import com.dcamarero.proyectomvc.models.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class User extends BaseEntity<User>
{
    @Column(unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    private LocalDateTime joinDate;

    @Column
    private LocalDateTime lastLogin;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller")
    private Set<Cart> carts;

    @Column
    private boolean activeUser;

    public User()
    {
        super();
    }

    @Override
    public int compareTo(BaseEntity<User> o)
    {
        return 0;
    }

    public User(String name, String email, String password, UserType userType, String dni, String surname, String phone, LocalDateTime joinDate, boolean activeUser)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.dni = dni;
        this.surname = surname;
        this.phone = phone;
        this.joinDate = joinDate;
        this.activeUser = activeUser;
    }

    public User(String name, String password, UserType userType, String dni, String surname, String phone, String email, LocalDateTime joinDate, LocalDateTime lastLogin, Set<Cart> carts, boolean activeUser)
    {
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.dni = dni;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.joinDate = joinDate;
        this.lastLogin = lastLogin;
        this.carts = carts;
        this.activeUser = activeUser;
    }

    @Override
    public String toString()
    {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", dni='" + dni + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                ", lastLogin=" + lastLogin +
                ", cartDTOS=" + carts +
                ", activeUser=" + activeUser +
                "} " + super.toString();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    public String getDni()
    {
        return dni;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDateTime getJoinDate()
    {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate)
    {
        this.joinDate = joinDate;
    }

    public LocalDateTime getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    public Set<Cart> getCarts()
    {
        return carts;
    }

    public void setCarts(Set<Cart> carts)
    {
        this.carts = carts;
    }

    public boolean isActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser)
    {
        this.activeUser = activeUser;
    }
}
