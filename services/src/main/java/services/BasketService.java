package services;

import pojos.Basket;

import java.util.List;

/**
 * Interface BasketService
 * <p>
 * Created by Yuraga on 15.10.2018
 */

public interface BasketService extends Service<Basket>{
    Basket createBasket(long userId, long partsId, int quantity, double price);

    List<Basket> getByUserId(long userId);

    List<Basket> getAll();
}
