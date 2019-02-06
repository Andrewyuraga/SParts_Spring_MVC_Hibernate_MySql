package dao.impl;

import dao.BasketDao;
import org.springframework.stereotype.Repository;
import pojos.Basket;
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
//        CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
//        CriteriaQuery<Basket> criteria = cb.createQuery(Basket.class);
//        Root<Basket> orderRoot = criteria.from(Basket.class);
//        criteria.select(orderRoot).where(cb.equal(orderRoot.get("id"), id));
//        return this.getEm().createQuery(criteria).getResultList();
        Query query = this.getEm().createQuery("from Basket b where b.userId= :userId");
        query.setParameter("userId", user);
        return query.getResultList();
    }


}
