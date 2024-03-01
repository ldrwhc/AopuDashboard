package aopu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchasing {// 代理商进货数据
    private String id; // UUID
    private String corporateName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
    private String category;
    private String goodName;
    private Integer quantity;
    private Double price;
    private Double discount;
}
