package aopu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salesman {  // 业务员
    private String id; // UUID
    private String province;
    private String city;
    private String corporateName;
    private String name;
    private String telephone;
    private Integer age;
    private String education;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;
    private String manageArea;

}
