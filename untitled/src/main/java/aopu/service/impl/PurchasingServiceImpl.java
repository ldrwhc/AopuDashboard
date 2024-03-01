package aopu.service.impl;

import aopu.mapper.PurchasingMapper;
import aopu.pojo.Purchasing;
import aopu.service.PurchasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasingServiceImpl implements PurchasingService {
    @Autowired
    PurchasingMapper purchasingMapper;

    @Override
    public void addPurchasing(Purchasing purchasing) {
        if (purchasingMapper.findById(purchasing.getId()) != null){
            System.out.println("记录已存在，更新记录");
            purchasingMapper.updatePurchasing(purchasing);
        } else {
            System.out.println("记录不存在，添加记录");
            purchasingMapper.addPurchasing(purchasing);
        }
    }

    @Override
    public void deletePurchasing(String id) {
        purchasingMapper.deletePurchasing(id);
    }

    @Override
    public void updatePurchasing(Purchasing purchasing) {
        purchasingMapper.updatePurchasing(purchasing);
    }

    @Override
    public List<Purchasing> findByName(String findName) {
        return purchasingMapper.findByName(findName);
    }

    @Override
    public List<Purchasing> findAll() {
        return purchasingMapper.findAll();
    }
}
