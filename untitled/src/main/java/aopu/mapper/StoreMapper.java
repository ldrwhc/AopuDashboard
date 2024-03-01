package aopu.mapper;

import aopu.pojo.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    List<Store> findByProvinceAndCity(String province, String city);

    void addStore(Store store);

    void deleteStore(String id);

    List<Store> findByName(String findName);

    Store findById(String id);

    void updateStore(Store store);
}
