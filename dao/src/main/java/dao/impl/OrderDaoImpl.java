package dao.impl;

import dao.OrderDao;
import org.springframework.stereotype.Repository;
import pojos.Order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yuraga
 */
@Repository
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao<Order> {
    public OrderDaoImpl() {
        super();
        clazz = Order.class;
    }

    public List<Order> getAll() {
        return this.getEm().createQuery("from Order ").getResultList();
    }

    public List<Order> getByUserId(Long id) {
        CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
        CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
        Root<Order> orderRoot = criteria.from(Order.class);
        criteria.select(orderRoot).where(cb.equal(orderRoot.get("id"), id));
        return this.getEm().createQuery(criteria).getResultList();
    }
}
