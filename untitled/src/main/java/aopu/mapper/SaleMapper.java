package aopu.mapper;

import aopu.pojo.Sale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleMapper {
    List<Sale> findByProvinceAndCity(String province, String city);

    void addSale(Sale sale);

    void deleteSale(String id);

    void updateSale(Sale sale);

    List<Sale> findAll();

    List<Sale> findByName(String findName);

    Sale findById(String id);

    void addUrl(String id, String orderImgUrl);
}
