package controller;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pojos.Parts;
import pojos.User;
import services.PartsService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Yuraga
 */
@Controller
public class HelloController {
    @Autowired
    public PartsService partsService;

    @Autowired
    public UserService userService;

    @Autowired
    @Qualifier("encoder")
    private PasswordEncoder encoder;

    private void fillModel(ModelMap model) {
        List<Parts> parts = partsService.getAll();
        model.put("parts", parts);
    }

    private void fillUserModel(ModelMap modelMap) {
        User user = new User();
        modelMap.put("user", user);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        fillModel(model);
        fillUserModel(model);
        return "welcome";
    }

    @RequestMapping(value = {"/login", ""}, method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        fillModel(model);
        fillUserModel(model);
        return "login";
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        fillModel(model);
        fillUserModel(model);
        model.addAttribute("user", getPrincipal());
        return "errorPassword";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @PostMapping(value = "/addUser")
    public String addUser(ModelMap modelMap, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "errorPassword";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user = userService.save(user);
        user = userService.get(user.getId());
        modelMap.put("user", user);
        return "welcome";
    }

    private String getPrincipal() {
        String login;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            login = ((UserDetails) principal).getUsername();
        } else {
            login = principal.toString();
        }
        return login;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("Spring MVC Handler");
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
