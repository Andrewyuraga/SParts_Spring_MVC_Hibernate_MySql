package services.impl;


import dao.ReviewDao;
import dao.UserDao;
import dao.impl.ReviewDaoImpl;
import dao.impl.UserDaoImpl;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Review;
import pojos.User;
import services.ReviewsService;
import services.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Class ReviewsServiceImpl
 * <p>
 * Created by Yuraga on 26.11.2018
 */
@Service
@Transactional
public class ReviewsServiceImpl extends BaseService<Review> implements ReviewsService {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Review> getAll() {
        return reviewDao.getAll();
    }

    @Override
    public Review createReview(long id, String text) {
        Review review = new Review();
        User user = (User) userDao.get(id);
        review.setUserId(user);
        review.setReview(text);
        reviewDao.save(review);
        return review;
    }
}
