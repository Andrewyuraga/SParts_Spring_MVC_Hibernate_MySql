package dao.impl;

import dao.BasketDao;
import org.springframework.stereotype.Repository;
import pojos.Basket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public List<Basket> getByUserId(Long id) {
        CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
        CriteriaQuery<Basket> criteria = cb.createQuery(Basket.class);
        Root<Basket> orderRoot = criteria.from(Basket.class);
        criteria.select(orderRoot).where(cb.equal(orderRoot.get("id"), id));
        return this.getEm().createQuery(criteria).getResultList();
    }
}
