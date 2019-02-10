package services.impl;

import dao.PartsDao;
import dao.impl.PartsDaoImpl;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojos.Parts;
import services.PartsService;
import services.ServiceException;

import java.util.List;

/**
 * Сlass PartsServiceImpl
 * <p>
 * Created by Yuraga
 */
@Service
@Transactional
public class PartsServiceImpl extends BaseService<Parts> implements PartsService {

    @Autowired
    private PartsDao partsDao;

    @Override
    public List<Parts> getByCategory(String category) {
        return partsDao.getByCategory(category);
    }


    @Override
    public List<Parts> getByProducerAndType(String producer, String type) {
            return partsDao.getByProducerAndType(producer, type);
    }

    @Override
    public List<Parts> getAll() {
            return partsDao.getAll();
    }


}
