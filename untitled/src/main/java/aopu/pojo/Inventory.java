package aopu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory { // 产品库
    private String id; // UUID
    private String category;
    private String goodName;
    private String factoryPrice;
    private String transactionPrice;
}
