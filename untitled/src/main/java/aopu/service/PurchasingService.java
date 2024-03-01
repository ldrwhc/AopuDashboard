package aopu.service;

import aopu.pojo.Purchasing;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 代理商进货数据
 */
@Service
public interface PurchasingService {
    void addPurchasing(Purchasing purchasing);

    void deletePurchasing(String id);

    void updatePurchasing(Purchasing purchasing);

    List<Purchasing> findByName(String findName);

    List<Purchasing> findAll();

}
