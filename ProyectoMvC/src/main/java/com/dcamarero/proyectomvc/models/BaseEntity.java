package com.dcamarero.proyectomvc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable, Comparable<BaseEntity<T>>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    /**
     * Instantiates a new Base entity.
     */
    public BaseEntity()
    {
        // no-args constructor
    }

    @JsonIgnore
    @Transient
    private T t;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    public Long getId()
    {
        return id;
    }

    @Override
    public int compareTo(BaseEntity<T> o)
    {
        return 0;
    }
}
