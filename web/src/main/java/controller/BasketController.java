package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import pojos.Basket;
import pojos.Parts;
import pojos.User;
import services.BasketService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Yuraga
 */
@Controller
public class BasketController {
    @Autowired
    private BasketService basketService;

    @Autowired
    private UserService userService;

    @PostMapping("/addBasket")
    public String addBasket(ModelMap modelMap, @Valid Basket basket,
                            @ModelAttribute Parts parts, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getBylogin(currentPrincipalName);

        basket = basketService.createBasket(user.getId(), parts.getId(), basket.getQuantity(), parts.getPrice());
        modelMap.put("user", basketService.get(basket.getId()));
        return "redirect:/";
    }


    @RequestMapping(value="/baskets", method = RequestMethod.GET)
    public String baskets(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getBylogin(currentPrincipalName);
        List<Basket> baskets = basketService.getByUserId(user.getId());
        modelMap.put("baskets", baskets);
        return "baskets";
    }

    @PostMapping("/deleteParts")
    public String deletePart(ModelMap modelMap, @ModelAttribute Basket basket, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        basketService.delete(basket.getId());
        return "redirect:/baskets";
    }
}
