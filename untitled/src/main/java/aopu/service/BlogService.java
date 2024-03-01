package aopu.service;

import aopu.mapper.BlogMapper;
import aopu.pojo.Blog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogService {
    @Resource
    private BlogMapper blogMapper;

    public void add(Blog blog){
        blogMapper.insert(blog);
    }

    public void deleteById(String id){
        blogMapper.deleteById(id);
    }

    public void deleteBatch(List<String> ids){
        for (String id : ids){
            blogMapper.deleteById(id);
        }
    }

    public void updateById(Blog blog){
        blogMapper.updateById(blog);
    }

    public Blog selectById(String id){
        return blogMapper.selectById(id);
    }

    public List<Blog> selectAll(){
        return blogMapper.selectAll();
    }
}
