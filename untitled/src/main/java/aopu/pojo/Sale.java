package aopu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale { // 门店销售统计
    private String id; // UUID
    private String province;
    private String city;
    private String agentName;
    private String storeName;
    private String salesman;
    private Double price;
    private String goodName;
    private Integer amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date writeDate;
    private String orderImgUrl;
}
