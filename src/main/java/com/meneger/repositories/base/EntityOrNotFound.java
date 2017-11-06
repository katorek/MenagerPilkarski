package com.meneger.repositories.base;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Optional;



public interface EntityOrNotFound {

    static <T, ID extends Serializable> T nullable(Class<T> clazz, ID id, T t) {
        return optional(clazz, id, Optional.ofNullable(t));
    }

    static <T, ID extends Serializable> T optional(Class<T> clazz, ID id, Optional<T> optional) {
        return optional.orElseThrow(() -> new EntityNotFoundException(notFoundMessage(clazz, id)));
    }

    static <T, ID extends Serializable> String notFoundMessage(Class<T> clazz, ID id) {
        return String.format(
                "Unable to find %s with id %s",
                clazz.getCanonicalName(), id);
    }
}
