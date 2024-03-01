package aopu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private String id;
    private String title;
    private String content;

    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

}
