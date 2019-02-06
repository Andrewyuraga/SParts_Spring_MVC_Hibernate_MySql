package controller;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        fillModel(model);
        return "welcome";
    }

    @PostMapping(value = "/addUser")
    public String addUser(ModelMap modelMap, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user = userService.save(user);
        modelMap.put("user", userService.get(user.getId()));
        return "welcome";
    }

    @RequestMapping(value = {"/login", ""}, method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        fillModel(model);
        return "login";
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        fillModel(model);
        model.addAttribute("user", getPrincipal());
        return "welcome";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
    private String getPrincipal(){
        String login;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            login = ((UserDetails)principal).getUsername();
        } else {
            login = principal.toString();
        }
        return login;
    }

}
