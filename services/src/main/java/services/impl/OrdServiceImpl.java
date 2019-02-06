package services.impl;

import dao.OrderDao;
import dao.PartsDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Order;
import pojos.Parts;
import pojos.User;
import services.OrdService;

import java.util.List;

/**
 * Сlass OrdServiceIpl
 * <p>
 * Created by Yuraga
 */
@Service
@Transactional
public class OrdServiceImpl extends BaseService<Order> implements OrdService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private PartsDao partsDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Order createOrder(long userId, long partsId, int quantity, double price) {
        Order order = new Order();
        User user = (User) userDao.get(userId);
        order.setUserId(user);
        if (quantity < 1) {
            quantity = 1;
        }
        Parts parts = (Parts) partsDao.get(partsId);
        order.setPartId(parts);
        order.setQuantity(quantity);
        order.setTotal(price * quantity);
        orderDao.save(order);
        return order;
    }

    @Override
    public List<Order> getByUserId(long userId) {
        return orderDao.getByUserId(userId);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
