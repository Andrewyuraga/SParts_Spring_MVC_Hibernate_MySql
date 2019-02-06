package dao;

import pojos.Review;

import java.util.List;

/**
 * Created by Yuraga
 */

public interface ReviewDao<T> extends DAO<T> {
    List<Review> getAll();
}
