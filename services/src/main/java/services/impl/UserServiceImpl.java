package services.impl;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.User;
import services.UserService;

import java.util.List;

/**
 * Class UserServiceImpl
 * <p>
 * Created by Yuraga on 17.10.2018
 */
@Service
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getBylogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

}
