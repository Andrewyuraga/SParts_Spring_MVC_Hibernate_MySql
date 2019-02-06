package services;

import pojos.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Interface OrdService
 * <p>
 * Created by Yuraga on 15.10.2018
 */

public interface OrdService extends Service<Order>{
    Order createOrder(long userId, long partsId, int quantity, double price);

    List<Order> getByUserId(long userId);

    List<Order> getAll();
}
