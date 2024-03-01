package aopu.service;

import aopu.pojo.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品库
 */
@Service
public interface InventoryService {
    void addInventory(Inventory inventory);

    void deleteInventory(String id);

    void updateInventory(Inventory inventory);

    List<Inventory> findByName(String findName);

    List<Inventory> findAll();

}
