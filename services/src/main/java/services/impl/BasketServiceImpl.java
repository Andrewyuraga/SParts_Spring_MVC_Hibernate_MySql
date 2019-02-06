package services.impl;

import dao.BasketDao;
import dao.PartsDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Basket;
import pojos.Parts;
import pojos.User;
import services.BasketService;

import java.util.Date;
import java.util.List;

/**
 * Сlass OrdServiceIpl
 * <p>
 * Created by Yuraga
 */
@Service
@Transactional
public class BasketServiceImpl extends BaseService<Basket> implements BasketService {
    @Autowired
    private BasketDao basketDao;
    @Autowired
    private PartsDao partsDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Basket createBasket(long userId, long partsId, int quantity, double price) {
        Basket basket = new Basket();
        User user = (User) userDao.get(userId);
        if (quantity < 1) {
            quantity = 1;
        }
        Parts parts = (Parts) partsDao.get(partsId);
        basket.setUserId(user);
        basket.setPartId(parts);
        basket.setQuantity(quantity);
        basket.setCost(price * quantity);
        basket.setDate(new Date());
        basketDao.save(basket);
        return basket;
    }

    @Override
    public List<Basket> getByUserId(long userId) {
        return basketDao.getByUserId(userId);
    }

    @Override
    public List<Basket> getAll() {
        return basketDao.getAll();
    }
}
