package services;

import pojos.Review;

import java.io.Serializable;
import java.util.List;

/**
 * Interface ReviewsService
 * <p>
 * Created by Yuraga on 26.11.2018
 */

public interface ReviewsService extends Service<Review>{

    List<Review> getAll();

    Review createReview(long id, String text);
}
