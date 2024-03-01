package aopu.service;

import aopu.pojo.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店销售统计
 */
@Service
public interface SaleService {
    List<Sale> findByProvinceAndCity(String province, String city);

    void addSale(Sale sale);

    void deleteSale(String id);

    void updateSale(Sale sale);

    List<Sale> findByName(String findName);

    List<Sale> findAll();

    void addUrl(String id, String url);
}
