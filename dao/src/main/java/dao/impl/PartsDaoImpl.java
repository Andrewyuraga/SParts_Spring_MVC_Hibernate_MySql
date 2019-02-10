package dao.impl;

import dao.PartsDao;
import org.springframework.stereotype.Repository;
import pojos.Order;
import pojos.Parts;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yuraga
 */
@Repository
public class PartsDaoImpl extends BaseDao<Parts> implements PartsDao<Parts> {
    public PartsDaoImpl() {
        super();
        clazz = Parts.class;
    }

    public List<Parts> getAll() {
        return this.getEm().createQuery("from Parts").getResultList();
    }

    public List<Parts> getByCategory(String category) {
        CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
        CriteriaQuery<Parts> criteria = cb.createQuery(Parts.class);
        Root<Parts> partRoot = criteria.from(Parts.class);
        criteria.select(partRoot).where(cb.equal(partRoot.get("category"), category));
        return this.getEm().createQuery(criteria).getResultList();
    }

    public List<Parts> getByProducerAndType(String producer, String type) {
        CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
        CriteriaQuery<Parts> criteria = cb.createQuery(Parts.class);
        Root<Parts> partRoot = criteria.from(Parts.class);
        Predicate predicate = cb.and(
                cb.equal(partRoot.<String>get("producer"), producer),
                cb.equal(partRoot.<String>get("type"), type));
        criteria.select(partRoot)
                .where(predicate);
        return this.getEm().createQuery(criteria).getResultList();
    }
}
