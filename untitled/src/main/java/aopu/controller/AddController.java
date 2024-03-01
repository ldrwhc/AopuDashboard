package aopu.controller;

import aopu.pojo.*;
import aopu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add")
@Validated
@CrossOrigin
public class AddController {

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

    @PostMapping("/addStore")
    @CrossOrigin
    public ResponseResult addStore(@RequestBody Store store){
        System.out.println("date"+store.getOpenTime());
        storeService.addStore(store);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/addAgent")
    @CrossOrigin
    public ResponseResult addAgent(@RequestBody Agent agent){
        System.out.println("添加了"+agent);
        agentService.addAgent(agent);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/addSale")
    @CrossOrigin
    public ResponseResult addSale(@RequestBody Sale sale){
        System.out.println("添加了"+sale);
        saleService.addSale(sale);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/addSalesman")
    @CrossOrigin
    public ResponseResult addSalesman(@RequestBody Salesman salesman){
        System.out.println("添加了"+salesman);
        salesmanService.addSalesman(salesman);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/addPurchasing")
    @CrossOrigin
    public ResponseResult addPurchasing(@RequestBody Purchasing purchasing){
        System.out.println("添加了"+purchasing);
        purchasingService.addPurchasing(purchasing);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/addInventory")
    @CrossOrigin
    public ResponseResult addInventory(@RequestBody Inventory inventory){
        System.out.println("添加了"+inventory);
        inventoryService.addInventory(inventory);
        return new ResponseResult(200,"添加成功");
    }

}
