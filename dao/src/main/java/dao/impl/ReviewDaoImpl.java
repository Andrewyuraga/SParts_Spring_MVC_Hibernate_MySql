package dao.impl;

import dao.ReviewDao;
import org.springframework.stereotype.Repository;
import pojos.Review;

import java.util.List;

/**
 * Created by Yuraga
 */
@Repository
public class ReviewDaoImpl extends BaseDao<Review> implements ReviewDao<Review> {
    public ReviewDaoImpl() {
        super();
        clazz = Review.class;
    }

    public List<Review> getAll() {
        return this.getEm().createQuery("from Review ").getResultList();
    }
}
