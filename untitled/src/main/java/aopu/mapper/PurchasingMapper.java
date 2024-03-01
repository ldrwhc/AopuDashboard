package aopu.mapper;

import aopu.pojo.Purchasing;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchasingMapper {
    void addPurchasing(Purchasing purchasing);

    void deletePurchasing(String id);

    void updatePurchasing(Purchasing purchasing);

    List<Purchasing> findByName(String findName);

    List<Purchasing> findAll();

    Purchasing findById(String id);
}
