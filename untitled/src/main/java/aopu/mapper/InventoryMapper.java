package aopu.mapper;

import aopu.pojo.Inventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryMapper {
    void addInventory(Inventory inventory);

    void deleteInventory(String id);

    void updateInventory(Inventory inventory);

    List<Inventory> findByName(String findName);

    Inventory findById(String id);

    List<Inventory> findAll();

}
