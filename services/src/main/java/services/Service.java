package services;

import java.io.Serializable;

/**
 * Created by Yuraga
 */

public interface Service<T> {
    T save(T t);

    T update(T t);

    T get(Serializable id);

    void delete(Serializable id);
}
