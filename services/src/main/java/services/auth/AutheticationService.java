package services.auth;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.User;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuraga
 */
@Service("authService")
public class AutheticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final User user = userService.getBylogin(login);
        System.out.println("User : " + user);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                State.ACTIVE.getState().equals(user.getState()), true, true, true,
                new ArrayList<GrantedAuthority>(){{
                    add(new SimpleGrantedAuthority("ROLE_" + user.getRight().toUpperCase()));

                }});
    }


}
