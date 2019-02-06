package dao;

import pojos.Basket;

import java.util.List;

/**
 * Created by Yuraga
 */

public interface BasketDao<T> extends DAO<T> {
    List<Basket> getAll();

    List<Basket> getByUserId(Long id);
}
