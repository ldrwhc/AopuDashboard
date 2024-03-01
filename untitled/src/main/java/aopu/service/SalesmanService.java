package aopu.service;

import aopu.pojo.Salesman;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务员
 */
@Service
public interface SalesmanService {
    List<Salesman> findByProvinceAndCity(String province, String city);

    void addSalesman(Salesman salesman);

    void deleteSalesman(String id);

    void updateSalesman(Salesman salesman);

    List<Salesman> findByName(String name);

    List<Salesman> findAll();

}
