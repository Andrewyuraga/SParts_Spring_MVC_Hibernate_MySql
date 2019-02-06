package services.impl;

import dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import services.Service;

import java.io.Serializable;

/**
 * Created by Yuraga
 */
@Transactional
public class BaseService<T> implements Service<T> {
    @Autowired
    private DAO<T> baseDao;

    public BaseService() {
    }

    @Autowired
    public BaseService(DAO<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public T save(T t) {
        return baseDao.save(t);
    }

    @Override
    public T update(T t) {
        return baseDao.update(t);
    }

    @Override
    public T get(Serializable id) {
        return baseDao.get(id);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }
}
