package aopu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {  // 代理商
    private String id; // UUID
    private String province;
    private String city;
    private String corporateName;
    private String agentName;
    private String telephone;
    private Integer age;
    private String education;
    private String agentBrand;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cooperationTime;
}
