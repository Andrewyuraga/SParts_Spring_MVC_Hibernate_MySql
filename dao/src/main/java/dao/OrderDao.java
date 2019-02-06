package dao;

import pojos.Order;

import java.util.List;

/**
 * Created by Yuraga
 */

public interface OrderDao<T> extends DAO<T> {
    List<Order> getAll();

    List<Order> getByUserId(Long id);
}
