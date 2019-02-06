package dao;

import pojos.Parts;

import java.util.List;

/**
 * Created by Yuraga
 */

public interface PartsDao<T> extends DAO<T>{
    List<Parts> getAll();

    List<Parts> getByCategory(String category);

    List<Parts> getByProducerAndType(String producer, String type);
}
