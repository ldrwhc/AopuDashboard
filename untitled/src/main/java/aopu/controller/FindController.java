package aopu.controller;

import aopu.pojo.*;
import aopu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/find")
@Validated
@CrossOrigin
public class FindController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private SaleService saleService;
    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PurchasingService purchasingService;

    @PostMapping("/findAll")
    @CrossOrigin
    public ResponseResult find(@RequestParam String province,@RequestParam String city){
        Map<String, Object> map = new HashMap<>();
        List<Store> store = storeService.findByProvinceAndCity(province,city);
        map.put("store",store);
        List<Agent> agent = agentService.findByProvinceAndCity(province,city);
        map.put("agent",agent);
        List<Sale> sale = saleService.findByProvinceAndCity(province,city);
        map.put("sale",sale);
        List<Salesman> salesman = salesmanService.findByProvinceAndCity(province,city);
        map.put("salesman",salesman);
        return new ResponseResult(200,map);
    }

    /**
     * @param requestMap 加在请求参数里
     * @param pageParam 分页参数，加在请求体里
     * @return
     */
    @PostMapping("/findAllStore")
    @CrossOrigin
    public ResponseResult findAllStore(@RequestParam HashMap requestMap,@RequestBody PageParam pageParam){
        int currPage = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        int totalLength = 0;
        Map<String, Object> map = new HashMap<>();
        List<Store> store = null;

        if ( requestMap.get("province") != null && requestMap.get("city")!=null){
            System.out.println(requestMap.get("province") + (String)requestMap.get("city"));
            String province = (String) requestMap.get("province");
            String city = (String) requestMap.get("city");
            store = storeService.findByProvinceAndCity(province,city);
        }
        if (requestMap.get("storeName") != null){
            System.out.println((String)requestMap.get("storeName"));
            String findName = (String) requestMap.get("storeName");
            store = storeService.findByName(findName);
        }

        totalLength = store.size();
//        System.out.println(store);
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = Math.min((currPage * pageSize), store.size());
        store = store.subList(firstIndex, lastIndex);
        //直接在list中截取
        map.put("store",store);
        map.put("storeLength",totalLength);
        return new ResponseResult(200,map);
    }

//    @PostMapping("/findStoreByName")
//    @CrossOrigin
//    public ResponseResult findStoreByName(@RequestParam String findName,@RequestBody PageParam pageParam){
//        int currPage = pageParam.getPageNum();
//        int pageSize = pageParam.getPageSize();
//        int totalLength = 0;
//        Map<String, Object> map = new HashMap<>();
//        List<Store> store = storeService.findByName(findName);
//        totalLength = store.size();
////        System.out.println(store);
//        //从第几条数据开始
//        int firstIndex = (currPage - 1) * pageSize;
//        //到第几条数据结束
//        int lastIndex = Math.min((currPage * pageSize), store.size());
//        store = store.subList(firstIndex, lastIndex);
//        //直接在list中截取
//        map.put("store",store);
//        map.put("storeLength",totalLength);
//        return new ResponseResult(200,map);
//    }

    @PostMapping("/findAllAgent")
    @CrossOrigin
    public ResponseResult findAllAgent(@RequestParam HashMap requestMap,@RequestBody PageParam pageParam){
        int currPage = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        int totalLength = 0;
        Map<String, Object> map = new HashMap<>();
        List<Agent> agent = null;

        if ( requestMap.get("province") != null && requestMap.get("city")!=null){
            System.out.println(requestMap.get("province") + (String)requestMap.get("city"));
            String province = (String) requestMap.get("province");
            String city = (String) requestMap.get("city");
            agent = agentService.findByProvinceAndCity(province,city);
        }
        if (requestMap.get("storeName") != null){
            System.out.println((String)requestMap.get("storeName"));
            String findName = (String) requestMap.get("storeName");
            agent = agentService.findByName(findName);
        }

        totalLength = agent.size();
//        System.out.println(store);
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = Math.min((currPage * pageSize), agent.size());
        agent = agent.subList(firstIndex, lastIndex);
        //直接在list中截取
        map.put("agent",agent);
        map.put("agentLength",totalLength);
        return new ResponseResult(200,map);
    }

    @PostMapping("/findAllSalesman")
    @CrossOrigin
    public ResponseResult findAllSalesman(@RequestParam HashMap requestMap,@RequestBody PageParam pageParam){
        int currPage = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        int totalLength = 0;
        Map<String, Object> map = new HashMap<>();
        List<Salesman> salesmen = null;

        if ( requestMap.get("province") != null && requestMap.get("city")!=null){
            System.out.println(requestMap.get("province") + (String)requestMap.get("city"));
            String province = (String) requestMap.get("province");
            String city = (String) requestMap.get("city");
            salesmen = salesmanService.findByProvinceAndCity(province,city);
        }else if (requestMap.get("storeName") != null){
            System.out.println((String)requestMap.get("storeName"));
            String findName = (String) requestMap.get("storeName");
            salesmen = salesmanService.findByName(findName);
        }else{
            System.out.println("找所有的");
            salesmen = salesmanService.findAll();
        }

        totalLength = salesmen.size();
//        System.out.println(store);
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = Math.min((currPage * pageSize), salesmen.size());
        salesmen = salesmen.subList(firstIndex, lastIndex);
        //直接在list中截取
        map.put("salesman",salesmen);
        map.put("salesmanLength",totalLength);
        return new ResponseResult(200,map);
    }

    @PostMapping("/findAllInventory")
    @CrossOrigin
    public ResponseResult findAllInventory(@RequestParam HashMap requestMap,@RequestBody PageParam pageParam){
        int currPage = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        int totalLength = 0;
        Map<String, Object> map = new HashMap<>();
        List<Inventory> inventory = null;

        if (requestMap.get("inventoryName") != null){
            System.out.println((String)requestMap.get("inventoryName"));
            String findName = (String) requestMap.get("inventoryName");
            inventory = inventoryService.findByName(findName);
        }else{
            System.out.println("找所有的");
            inventory = inventoryService.findAll();
        }

        totalLength = inventory.size();
//        System.out.println(store);
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = Math.min((currPage * pageSize), inventory.size());
        inventory = inventory.subList(firstIndex, lastIndex);
        //直接在list中截取
        map.put("inventory",inventory);
        map.put("inventoryLength",totalLength);
        return new ResponseResult(200,map);
    }

    @PostMapping("/findAllPurchasing")
    @CrossOrigin
    public ResponseResult findAllPurchasing(@RequestParam HashMap requestMap,@RequestBody PageParam pageParam){
        int currPage = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        int totalLength = 0;
        Map<String, Object> map = new HashMap<>();
        List<Purchasing> purchasing = null;

        if (requestMap.get("purchasingName") != null){
            System.out.println((String)requestMap.get("purchasingName"));
            String findName = (String) requestMap.get("purchasingName");
            purchasing = purchasingService.findByName(findName);
        }else{
            System.out.println("找所有的");
            purchasing = purchasingService.findAll();
        }

        totalLength = purchasing.size();
//        System.out.println(store);
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = Math.min((currPage * pageSize), purchasing.size());
        purchasing = purchasing.subList(firstIndex, lastIndex);
        //直接在list中截取
        map.put("purchasing",purchasing);
        map.put("purchasingLength",totalLength);
        return new ResponseResult(200,map);
    }


    @PostMapping("/findAllSale")
    @CrossOrigin
    public ResponseResult findAllSale(@RequestParam HashMap requestMap,@RequestBody PageParam pageParam){
        int currPage = pageParam.getPageNum();
        int pageSize = pageParam.getPageSize();
        int totalLength = 0;
        Map<String, Object> map = new HashMap<>();
        List<Sale> sale = null;

        if ( requestMap.get("province") != null && requestMap.get("city")!=null){
            System.out.println(requestMap.get("province") + (String)requestMap.get("city"));
            String province = (String) requestMap.get("province");
            String city = (String) requestMap.get("city");
            sale = saleService.findByProvinceAndCity(province,city);
        }else if (requestMap.get("saleName") != null){
            System.out.println((String)requestMap.get("saleName"));
            String findName = (String) requestMap.get("saleName");
            sale = saleService.findByName(findName);
        }else{
            System.out.println("找所有的");
            sale = saleService.findAll();
        }

        totalLength = sale.size();
//        System.out.println(store);
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = Math.min((currPage * pageSize), sale.size());
        sale = sale.subList(firstIndex, lastIndex);
        //直接在list中截取
        map.put("sale",sale);
        map.put("saleLength",totalLength);
        return new ResponseResult(200,map);
    }


}
