import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.User;
import services.UserService;

import java.util.List;

/**
 * Created by Yuraga
 */

@ContextConfiguration("/beans-service-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    @Rollback(false)
    public void allTest() {
        User user = new User("jdjsjsd", "dshdhsd", "sddssdsd");
        userService.save(user);
        Assert.assertNotNull(user);
        User userGet = userService.get(user.getId());
        userGet.setLogin("Andrei");
        userService.update(userGet);
        Assert.assertEquals("Error update! ", "Andrei", userGet.getLogin());
        User userGetByEmail = userService.getBylogin("Andrei");
        Assert.assertNotNull(userGetByEmail);
        List<User> users = userService.getAll();
        for(int i = 0; i < users.size(); i++) {
            Assert.assertNotNull(users.get(i));
            System.out.println(users.get(i));
//            userService.delete(users.get(i).getId());
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateTest() {
        User user = userService.get(4L); //replase id for this test
        Assert.assertNotNull(user);
        user.setLogin("Hata");
        userService.update(user);
        System.out.println(user);
        User user1 = userService.getBylogin("Hata");
        System.out.println(user1);
        userService.delete(user.getId());
    }
}
