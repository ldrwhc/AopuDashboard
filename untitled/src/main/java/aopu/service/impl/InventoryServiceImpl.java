package aopu.service.impl;

import aopu.mapper.InventoryMapper;
import aopu.pojo.Inventory;
import aopu.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    InventoryMapper inventoryMapper;

    @Override
    public void addInventory(Inventory inventory) {
        if (inventoryMapper.findById(inventory.getId()) != null){
            System.out.println("记录已存在，更新记录");
            inventoryMapper.updateInventory(inventory);
        } else {
            System.out.println("记录不存在，添加记录");
            inventoryMapper.addInventory(inventory);
        }
    }

    @Override
    public void deleteInventory(String id) {
        inventoryMapper.deleteInventory(id);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        inventoryMapper.updateInventory(inventory);
    }

    @Override
    public List<Inventory> findByName(String findName) {
        return inventoryMapper.findByName(findName);
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryMapper.findAll();
    }
}
