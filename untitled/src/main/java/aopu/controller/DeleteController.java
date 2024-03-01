package aopu.controller;

import aopu.pojo.*;
import aopu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete")
@Validated
@CrossOrigin
public class DeleteController {
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

    @GetMapping("/deleteStore")
    public ResponseResult deleteStore(@RequestParam String id){
        storeService.deleteStore(id);
        return new ResponseResult(200,"删除成功");
    }

    @GetMapping("/deleteAgent")
    public ResponseResult deleteAgent(@RequestParam String id){
        agentService.deleteAgent(id);
        return new ResponseResult(200,"添加成功");
    }

    @GetMapping("/deleteSale")
    public ResponseResult deleteSale(@RequestParam String id){
        saleService.deleteSale(id);
        return new ResponseResult(200,"添加成功");
    }

    @GetMapping("/deleteSalesman")
    public ResponseResult deleteSalesman(@RequestParam String id){
        salesmanService.deleteSalesman(id);
        return new ResponseResult(200,"添加成功");
    }

    @GetMapping("/deletePurchasing")
    public ResponseResult deletePurchasing(@RequestParam String id){
        purchasingService.deletePurchasing(id);
        return new ResponseResult(200,"添加成功");
    }

    @GetMapping("/deleteInventory")
    public ResponseResult deleteInventory(@RequestParam String id){
        inventoryService.deleteInventory(id);
        return new ResponseResult(200,"添加成功");
    }
}
