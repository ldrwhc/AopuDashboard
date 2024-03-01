package aopu.service;

import aopu.pojo.Store;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店信息
 */
@Service
public interface StoreService {
    List<Store> findByProvinceAndCity(String province, String city);

    void addStore(Store store);

    void deleteStore(String id);

    List<Store> findByName(String findName);

    void updateStore(Store store);
}
