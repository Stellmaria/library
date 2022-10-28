package com.it.academy.library.model.entity;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> extends Serializable {
    /**
     * Getter.
     *
     * @return id.
     */
    T getId();

    /**
     * Setter.
     *
     * @param id for update id.
     */
    void setId(T id);
}
