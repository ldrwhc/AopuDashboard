package aopu.pojo;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store { // 门店信息
    @Alias("id")
    private String id; // UUID
    @Alias("省")
    private String province;
    @Alias("市")
    private String city;
    @Alias("公司名称")
    private String corporateName;
    @Alias("门店名称")
    private String storeName;
    @Alias("门店类别")
    private String storeClass;
    // 门店面积
    @Alias("面积")
    private String area;
    //市场分类
    @Alias("市场分类")
    private String segmentation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Alias("开业时间")
    private Date openTime;
    // 导购
    @Alias("导购")
    private String shopGuide;
    @Alias("联系电话")
    private String telephone;
}
