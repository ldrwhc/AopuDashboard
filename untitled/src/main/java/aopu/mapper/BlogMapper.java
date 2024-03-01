package aopu.mapper;

import aopu.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    int insert(Blog blog);

    int deleteById(String id);

    int updateById(Blog blog);

    Blog selectById(String id);

    List<Blog> selectAll();
}
