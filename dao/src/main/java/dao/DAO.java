package dao;

import java.io.Serializable;

/**
 * Interface DAO
 * <p>
 * Created by Yuraga on 15/10/2018
 */

public interface DAO<T> {
    T save(T t);

    T get(Serializable id);

    T update(T t);

    void delete(Serializable id);
}
