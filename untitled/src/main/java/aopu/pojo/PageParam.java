package aopu.pojo;

import lombok.Data;

/**
 * 分页参数
 */
@Data
public class PageParam {
    private static int PAGE_SIZE = 20;
    private static int PAGE_NUM = 1;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;
}
