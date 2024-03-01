package aopu.mapper;

import aopu.pojo.Salesman;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SalesmanMapper {
    List<Salesman> findByProvinceAndCity(String province, String city);

    void addSalesman(Salesman salesman);

    void deleteSalesman(String id);

    void updateSalesman(Salesman salesman);

    Salesman findById(String id);

    List<Salesman> findByName(String name);

    List<Salesman> findAll();
}
