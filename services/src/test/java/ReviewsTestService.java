import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.Review;
import pojos.User;
import services.ReviewsService;
import services.UserService;

import java.util.List;

/**
 * Created by Yuraga
 */
@ContextConfiguration("/beans-service-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReviewsTestService {
    @Autowired
    private ReviewsService reviewsService;
    @Autowired
    private UserService userService;

    @Transactional
    @Test
    public void saveTest() {
        User user = new User("hdsghd", "qwerty", "qwerty");
        userService.save(user);
        Review review = new Review(null, user, "wqewqewe");
        reviewsService.save(review);
        Review review1 = reviewsService.get(review.getId());
        Assert.assertNotNull(review1);
        review1.setReview("shjhshsssss");
        reviewsService.update(review1);
        Assert.assertEquals("Error update ", "shjhshsssss", review1.getReview());
        List<Review> reviews = reviewsService.getAll();
        for(int i = 0; i < reviews.size(); i++ ) {
            Assert.assertNotNull(reviews.get(i));
            reviewsService.delete(reviews.get(i).getId());
        }
    }
}
