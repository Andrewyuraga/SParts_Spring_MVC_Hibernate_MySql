package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojos.Review;
import services.ReviewsService;

import java.util.List;

/**
 * Created by Yuraga
 */
@Controller
public class ReviewsConroller {
    @Autowired
    private ReviewsService reviewsService;

    private void fillModel(ModelMap model) {
        List<Review> reviews = reviewsService.getAll();
        model.put("reviews", reviews);
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        fillModel(model);
        return "review";
    }
}
