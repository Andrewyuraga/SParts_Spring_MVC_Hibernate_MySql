package services;

import pojos.User;

import java.io.Serializable;
import java.util.List;

/**
 * Interface UserService
 * <p>
 * Created by Yuraga on 15.10.2018
 */

public interface UserService extends Service<User>{

    User getBylogin(String login);

    List<User> getAll();
}

