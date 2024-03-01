package aopu.controller;

import aopu.pojo.*;
import aopu.service.*;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/upload")
@Validated
@CrossOrigin
public class UploadController {
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

    /**
     * 批量导入
     * @param file 文件对象
     * @return 导入结果
     * @throws IOException
     */
    @PostMapping("/uploadStore")
    @CrossOrigin
    public ResponseResult uploadStore(@RequestParam(value = "file",required = false) MultipartFile file){
        System.out.println("data:"+file.getOriginalFilename());
        try{
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<Store> stores = reader.readAll(Store.class);
            for (Store store:stores){
                storeService.addStore(store);
            }
            return new ResponseResult(200,"添加成功");
        }catch (IOException e){
            return new ResponseResult(500,e.getMessage());
        }
    }

    /**
     * 批量导入
     * @param file 文件对象
     * @return 导入结果
     * @throws IOException
     */
    @PostMapping("/uploadAgent")
    @CrossOrigin
    public ResponseResult uploadAgent(@RequestParam(value = "file",required = false) MultipartFile file){
        System.out.println("data:"+file.getOriginalFilename());
        try{
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<Agent> agents = reader.readAll(Agent.class);
            for (Agent agent:agents){
                agentService.addAgent(agent);
            }
            return new ResponseResult(200,"添加成功");
        }catch (IOException e){
            return new ResponseResult(500,e.getMessage());
        }
    }

    /**
     * 批量导入
     * @param file 文件对象
     * @return 导入结果
     * @throws IOException
     */
    @PostMapping("/uploadInventory")
    @CrossOrigin
    public ResponseResult uploadInventory(@RequestParam(value = "file",required = false) MultipartFile file){
        System.out.println("data:"+file.getOriginalFilename());
        try{
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<Inventory> inventories = reader.readAll(Inventory.class);
            for (Inventory inventory:inventories){
                inventoryService.addInventory(inventory);
            }
            return new ResponseResult(200,"添加成功");
        }catch (IOException e){
            return new ResponseResult(500,e.getMessage());
        }
    }

    /**
     * 批量导入
     * @param file 文件对象
     * @return 导入结果
     * @throws IOException
     */
    @PostMapping("/uploadPurchasing")
    @CrossOrigin
    public ResponseResult uploadPurchasing(@RequestParam(value = "file",required = false) MultipartFile file){
        System.out.println("data:"+file.getOriginalFilename());
        try{
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<Purchasing> purchasings = reader.readAll(Purchasing.class);
            for (Purchasing purchasing:purchasings){
                purchasingService.addPurchasing(purchasing);
            }
            return new ResponseResult(200,"添加成功");
        }catch (IOException e){
            return new ResponseResult(500,e.getMessage());
        }
    }

    /**
     * 批量导入
     * @param file 文件对象
     * @return 导入结果
     * @throws IOException
     */
    @PostMapping("/uploadSalesman")
    @CrossOrigin
    public ResponseResult uploadSalesman(@RequestParam(value = "file",required = false) MultipartFile file){
        System.out.println("data:"+file.getOriginalFilename());
        try{
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<Salesman> salesmen = reader.readAll(Salesman.class);
            for (Salesman salesman:salesmen){
                salesmanService.addSalesman(salesman);
            }
            return new ResponseResult(200,"添加成功");
        }catch (IOException e){
            return new ResponseResult(500,e.getMessage());
        }
    }



}
