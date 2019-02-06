import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.Parts;
import services.PartsService;

import java.util.List;

/**
 * Created by Yuraga
 */
@ContextConfiguration("/beans-service-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PartsServiceTest {
    @Autowired
    private PartsService partsService;

    @Transactional
    @Test
    public void saveTest() {
        Parts parts = new Parts("fdddf", "fddffd", "fddfdfsd", "ffdsa", "fdfdf", 123.3);
        partsService.save(parts);
        Parts parts1 = partsService.get(parts.getId());
        Assert.assertNotNull(parts1);
        parts1.setCategory("oil");
        partsService.update(parts1);
        Assert.assertEquals("Error update ", "oil", parts1.getCategory());
        List<Parts> partsList = partsService.getByProducerAndType(parts1.getProducer(), parts1.getType());
        for (int i = 0; i < partsList.size(); i++) {
            Assert.assertNotNull(partsList.get(i));
        }
        List<Parts> getByCategory = partsService.getByCategory("oil");
        for (int i = 0; i < getByCategory.size(); i++) {
            Assert.assertNotNull(getByCategory.get(i));
        }
        List<Parts> getAll = partsService.getAll();
        for (int i = 0; i < getAll.size(); i++) {
            Assert.assertNotNull(getAll.get(i));
            partsService.delete(getAll.get(i).getId());
        }
    }
}
