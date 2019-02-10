package dao.impl;

import dao.BasketDao;
import enums.Condition;
import org.springframework.stereotype.Repository;
import pojos.Basket;
import pojos.Order;
import pojos.User;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Yuraga
 */
@Repository
public class BasketDaoImpl extends BaseDao<Basket> implements BasketDao<Basket> {
    public BasketDaoImpl() {
        super();
        clazz = Basket.class;
    }

    public List<Basket> getAll() {
        return this.getEm().createQuery("from Basket ").getResultList();
    }

    public List<Basket> getByUserId(User user) {
        Query query = this.getEm().createQuery("from Basket b where b.userId= :userId and b.condition= :condition")
                .setParameter("userId", user)
                .setParameter("condition", "Active");
        return query.getResultList();
    }

    public Double basketSum(User user) {
        Query query = this.getEm().createQuery("select sum(b.cost) from Basket b where b.userId= :userId " +
                "and b.condition= :condition")
                .setParameter("userId", user)
                .setParameter("condition", "Active");
        Double sum = (Double) query.getSingleResult();
        return sum;
    }
}
