package com.dcamarero.proyectomvc.models;

import com.dcamarero.proyectomvc.models.enums.EstadoType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode
@Entity
public class Cart extends BaseEntity<Cart>
{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartEntry> cartEntries;*/
    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    @Cascade(value = {CascadeType.ALL})
    private Set<CartEntry> cartEntries;

    @Column(nullable = false)
    private double total;


    @Column(nullable = false)
    private LocalDateTime sellDate;

    @Column
    String direccion;

    @Enumerated(EnumType.STRING)
    private EstadoType estado;


    public Cart()
    {
        super();
    }

    public Cart(User seller, Cliente cliente, Set<CartEntry> cartEntries, double total, LocalDateTime sellDate, String direccion, EstadoType estado)
    {
        this.seller = seller;
        this.cliente = cliente;
        this.cartEntries = cartEntries;
        this.total = total;
        this.sellDate = sellDate;
        this.direccion = direccion;
        this.estado = estado;
    }

    @Override
    public int compareTo(BaseEntity<Cart> o)
    {
        return 0;
    }


    @Override
    public String toString()
    {
        return "CartDTO{" +
                "seller=" + seller +
                ", clienteDTO=" + cliente +
                ", cartDTO=" + cartEntries +
                ", total=" + total +
                ", sellDate=" + sellDate +
                ", direccion='" + direccion + '\'' +
                ", estado=" + estado +
                "} " + super.toString();
    }

    public User getSeller()
    {
        return seller;
    }

    public void setSeller(User seller)
    {
        this.seller = seller;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente clienteDTO)
    {
        this.cliente = clienteDTO;
    }

    public Set<CartEntry> getCartEntries()
    {
        return cartEntries;
    }

    public void setCartEntries(Set<CartEntry> cartEntries)
    {
        this.cartEntries = cartEntries;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public LocalDateTime getSellDate()
    {
        return sellDate;
    }

    public void setSellDate(LocalDateTime sellDate)
    {
        this.sellDate = sellDate;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public EstadoType getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoType estado)
    {
        this.estado = estado;
    }
}
