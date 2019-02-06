import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.User;

/**
 * Created by Yuraga
 */
@ContextConfiguration("/beans-dao-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoTest {
    @Autowired
    private UserDaoImpl userDao;

    @Test
    @Transactional
    @Rollback(false)
    public void saveTest() {
        User user = new User("jdjssd", "dshdhsd", "sddssdsd");
        userDao.save(user);
        System.out.println(userDao.getAll());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateTest() {
        User user = userDao.get(1L);
        Assert.assertNotNull(user);
        user.setLogin("Hata");
        userDao.update(user);
        System.out.println(user);
        User user1 = userDao.getByLogin("Hata");
        System.out.println(user1);
        userDao.delete(user.getId());
    }
}
