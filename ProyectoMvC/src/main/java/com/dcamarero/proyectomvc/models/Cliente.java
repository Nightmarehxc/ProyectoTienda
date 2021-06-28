package com.dcamarero.proyectomvc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The type Partner.
 */
@Entity
public class Cliente extends BaseEntity<Cliente>
{
    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String genderType;

    @Column(nullable = false)
    private String phone;
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column(nullable = false)
    private LocalDateTime joinDate;

    @Column
    private LocalDateTime lastLogin;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private Set<Cart> carts;

    @Column(nullable = false)
    private boolean active;


    public Cliente()
    {
        super();
    }

    public Cliente(String dni, String name, String surname, String genderType, String phone, String password, String alias, LocalDateTime joinDate, LocalDateTime lastLogin, Set<Cart> carts, boolean active)
    {
        super();
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.genderType = genderType;
        this.phone = phone;
        this.password = password;
        this.email = alias;
        this.joinDate = joinDate;
        this.lastLogin = lastLogin;
        this.carts = carts;
        this.active = active;
    }

    public Cliente(String dni, String name, String surname, String genderType, String phone, String email, LocalDateTime joinDate, LocalDateTime lastLogin, Set<Cart> carts, boolean active)
    {
        super();
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.genderType = genderType;
        this.phone = phone;
        this.email = email;
        this.joinDate = joinDate;
        this.lastLogin = lastLogin;
        this.carts = carts;
        this.active = active;
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public int compareTo(BaseEntity o)
    {
        return 0; //TODO
    }

    @Override
    public String toString()
    {
        return "Partner{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname1='" + surname + '\'' +
                ", genderType='" + genderType + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                ", lastVisit=" + lastLogin +
                ", active=" + active +
                '}';
    }

    public String getDni()
    {
        return dni;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getGenderType()
    {
        return genderType;
    }

    public void setGenderType(String genderType)
    {
        this.genderType = genderType;
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

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}

