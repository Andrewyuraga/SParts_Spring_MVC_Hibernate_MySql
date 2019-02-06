package dao;

import pojos.User;

import java.util.List;

/**
 * Created by Yuraga
 */

public interface UserDao<T> extends DAO<T>{
    User getByLogin(String login);

    List<User> getAll();
}
