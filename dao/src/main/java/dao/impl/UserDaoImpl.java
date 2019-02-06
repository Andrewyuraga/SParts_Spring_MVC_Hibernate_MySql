package dao.impl;

import dao.UserDao;
import org.springframework.stereotype.Repository;
import pojos.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yuraga
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao<User> {
    public UserDaoImpl() {
        super();
        clazz = User.class;
    }

    public User getByLogin(String login) {
        CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        Predicate predicate = cb.and(
                cb.like(userRoot.<String>get("login"), login));
        criteria.select(userRoot)
                .where(predicate);
        return this.getEm().createQuery(criteria).getSingleResult();
    }

    public List<User> getAll() {
        return this.getEm().createQuery("from User").getResultList();
    }
}
