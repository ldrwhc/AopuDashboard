package aopu.service.impl;

import aopu.mapper.StoreMapper;
import aopu.pojo.Store;
import aopu.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreMapper storeMapper;
    @Override
    public List<Store> findByProvinceAndCity(String province, String city) {
        return storeMapper.findByProvinceAndCity(province,city);
    }

    @Override
    public void addStore(Store store) {
        if (storeMapper.findById(store.getId()) != null){
            System.out.println("记录已存在，更新记录");
            storeMapper.updateStore(store);
        } else {
            System.out.println("记录不存在，添加记录");
            storeMapper.addStore(store);
        }
    }

    @Override
    public void deleteStore(String id) {
        storeMapper.deleteStore(id);
    }

    @Override
    public List<Store> findByName(String findName) {
        return storeMapper.findByName(findName);
    }

    @Override
    public void updateStore(Store store) {
        storeMapper.updateStore(store);
    }
}
