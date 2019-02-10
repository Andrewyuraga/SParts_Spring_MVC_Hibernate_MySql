package controller;

import enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojos.Basket;
import pojos.Order;
import pojos.Parts;
import pojos.User;
import services.BasketService;
import services.OrderService;
import services.PartsService;
import services.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Yuraga
 */
@Controller
public class AdminController {
    @Autowired
    private BasketService basketService;

    @Autowired
    private PartsService partsService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/allBaskets", method = RequestMethod.GET)
    public String allBaskets(ModelMap modelMap) {
        List<Basket> baskets = basketService.getAll();
        modelMap.addAttribute("baskets", baskets);
        return "allBaskets";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProductPage(ModelMap model) {
        return "addProduct";
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping(value = "/addParts")
    public String addParts(ModelMap modelMap, @Valid Parts parts, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        parts = partsService.save(parts);
        modelMap.put("parts", partsService.get(parts.getId()));
        return "addProduct";
    }

    @RequestMapping(value = "/allOrders", method = RequestMethod.GET)
    public String allOrders(ModelMap modelMap) {
        List<Order> orders = orderService.getAll();
        modelMap.addAttribute("orders", orders);
        return "allOrders";
    }

    @PostMapping(value = "/changeStatus")
    public  String changeStatus(ModelMap modelMap, @Valid Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        order = orderService.get(order.getId());
        order.setStatus(Status.CLOSE.getStatus());
        orderService.update(order);
        return "redirect:/allOrders";
    }
}
