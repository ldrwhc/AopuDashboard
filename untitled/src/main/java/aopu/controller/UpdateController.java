package aopu.controller;

import aopu.pojo.*;
import aopu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update")
@Validated
@CrossOrigin
public class UpdateController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PurchasingService purchasingService;
    @Autowired
    private SalesmanService salesmanService;

    @PostMapping("/updateStore")
    @CrossOrigin
    public ResponseResult updateStore(@RequestBody Store store){
        System.out.println("修改了"+store);
        storeService.updateStore(store);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/updateAgent")
    @CrossOrigin
    public ResponseResult updateAgent(@RequestBody Agent agent){
        agentService.updateAgent(agent);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/updateSale")
    public ResponseResult updateSale(@RequestBody Sale sale){
        saleService.updateSale(sale);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/updateSalesman")
    public ResponseResult updateSalesman(@RequestBody Salesman salesman){
        salesmanService.updateSalesman(salesman);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/updatePurchasing")
    public ResponseResult updatePurchasing(@RequestBody Purchasing purchasing){
        purchasingService.updatePurchasing(purchasing);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/updateInventory")
    public ResponseResult updateInventory(@RequestBody Inventory inventory){
        inventoryService.updateInventory(inventory);
        return new ResponseResult(200,"添加成功");
    }
}
