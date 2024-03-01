package aopu.service.impl;

import aopu.mapper.SalesmanMapper;
import aopu.pojo.Salesman;
import aopu.service.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesmanServiceImpl implements SalesmanService {
    @Autowired
    SalesmanMapper salesmanMapper;
    @Override
    public List<Salesman> findByProvinceAndCity(String province, String city) {
        return salesmanMapper.findByProvinceAndCity(province,city);
    }

    @Override
    public void addSalesman(Salesman salesman) {
        if (salesmanMapper.findById(salesman.getId()) != null){
            System.out.println("记录已存在，更新记录");
            salesmanMapper.updateSalesman(salesman);
        } else {
            System.out.println("记录不存在，添加记录");
            salesmanMapper.addSalesman(salesman);
        }
    }



    @Override
    public void deleteSalesman(String id) {
        salesmanMapper.deleteSalesman(id);
    }

    @Override
    public void updateSalesman(Salesman salesman) {
        salesmanMapper.updateSalesman(salesman);
    }

    @Override
    public List<Salesman> findByName(String name) {
        return salesmanMapper.findByName(name);
    }

    @Override
    public List<Salesman> findAll() {
        return salesmanMapper.findAll();
    }
}
