package services;

import pojos.Parts;

import java.io.Serializable;
import java.util.List;

/**
 * Interface PartsService
 * <p>
 * Created by Yuraga on 15.10.2018
 */

public interface PartsService extends Service<Parts>{

    List<Parts> getByProducerAndType(String producer, String type);

    List<Parts> getAll();

    List<Parts> getByCategory(String category);
}

