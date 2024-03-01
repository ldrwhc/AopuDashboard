package aopu.controller;

import aopu.pojo.Blog;
import aopu.pojo.ResponseResult;
import aopu.service.BlogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/blog")
@Validated
@CrossOrigin
public class BlogController {
    @Resource
    private BlogService blogService;

    @PostMapping("/add")
    @CrossOrigin
    public ResponseResult add(@RequestBody Blog blog){
        System.out.println("Date"+blog.getDate());
        blogService.add(blog);
        return new ResponseResult(200,"添加成功！");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable String id){
        System.out.println("id"+id);
        blogService.deleteById(id);
        return new ResponseResult(200,"删除成功");
    }

    /**
     * 批量删除
     * @param ids 删除id
     * @return ResponseResult
     */
    @DeleteMapping("/delete/batch")
    public ResponseResult deleteBatch(@RequestBody List<String> ids){
        blogService.deleteBatch(ids);
        return new ResponseResult(200,"成功");
    }

    @PutMapping("/update")
    public ResponseResult updateById(@RequestBody Blog blog){
        blogService.updateById(blog);
        return new ResponseResult(200,"成功");
    }

    @GetMapping("/selectById/{id}")
    public ResponseResult selectById(@PathVariable String id){
        Blog blog = blogService.selectById(id);
        return new ResponseResult(200,"查找成功");
    }

    @GetMapping("/selectAll")
    public ResponseResult selectAll(){
        List<Blog> list = blogService.selectAll();
        return new ResponseResult(200,list);
    }

//    @GetMapping("/selectPage")
//    public ResponseResult selectPage(Blog blog,
//                                     @RequestParam(defaultValue = "1") Integer pageNum,
//                                     @RequestParam(defaultValue = "10") Integer pageSize){
//        Page
//    }
}
