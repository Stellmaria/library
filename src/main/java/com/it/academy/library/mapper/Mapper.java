package com.it.academy.library.mapper;

public interface Mapper<F, T> {
    /**
     * Converts one object to another.
     *
     * @param object from.
     * @return to.
     */
    T map(F object);

    /**
     * Converts one object to another.
     *
     * @param fromObject from.
     * @param toObject   to.
     * @return to.
     */
    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
