package aopu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String nickname;
    private String password;
    private String corporateName;
    private String storeName;
    private String telephone;
}
