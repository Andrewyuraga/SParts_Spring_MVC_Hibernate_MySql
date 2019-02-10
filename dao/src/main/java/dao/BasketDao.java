package dao;

import pojos.Basket;
import pojos.User;

import java.util.List;

/**
 * Created by Yuraga
 */

public interface BasketDao<T> extends DAO<T> {
    List<Basket> getAll();

    List<Basket> getByUserId(User user);

    Double basketSum(User user);

    List<Basket> getOrderedBasket(User user);
}
