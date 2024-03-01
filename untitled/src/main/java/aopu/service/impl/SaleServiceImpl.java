package aopu.service.impl;

import aopu.mapper.SaleMapper;
import aopu.pojo.Sale;
import aopu.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    SaleMapper saleMapper;
    @Override
    public List<Sale> findByProvinceAndCity(String province, String city) {
        return saleMapper.findByProvinceAndCity(province,city);
    }

    @Override
    public void addSale(Sale sale) {
        if (saleMapper.findById(sale.getId()) != null){
            System.out.println("记录已存在，更新记录");
            saleMapper.updateSale(sale);
        } else {
            System.out.println("记录不存在，添加记录");
            saleMapper.addSale(sale);
        }
    }

    @Override
    public void deleteSale(String id) {
        saleMapper.deleteSale(id);
    }

    @Override
    public void updateSale(Sale sale) {
        saleMapper.updateSale(sale);
    }

    @Override
    public List<Sale> findByName(String findName) {
        return saleMapper.findByName(findName);
    }

    @Override
    public List<Sale> findAll() {
        return saleMapper.findAll();
    }

    @Override
    public void addUrl(String id, String url) {
        saleMapper.addUrl(id,url);
    }
}
