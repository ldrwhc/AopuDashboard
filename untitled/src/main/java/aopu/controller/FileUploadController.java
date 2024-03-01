package aopu.controller;

import aopu.pojo.ResponseResult;
import aopu.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    SaleService saleService;

    @PostMapping("/upload")
    @CrossOrigin
    public ResponseResult uploadFile(@RequestParam(value = "file",required = false)MultipartFile files,@RequestParam("id")String id){
        System.out.println("id"+ id);

        //判断非空
        if (files.isEmpty()){
            return new ResponseResult(400,"文件不能为空");
        }
        String originalFilename = files.getOriginalFilename();
        //获取系统时间戳+原始文件的后缀名
        String fileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //设置保存地址
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String path = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath()+
                "\\src\\main\\resources\\static\\images\\";
//        String path = "http://localhost:8089/static/images";
//        String path = "src/main/resources/static/images/";
        System.out.println("path"+path);
        System.out.println("fileName"+fileName);
        saleService.addUrl(id,"http://localhost:8089/images/" + fileName);
        File dest = new File(path + fileName);

        //判断文件是否存在
        if (!dest.getParentFile().exists()){
            //不存在就创建一个
            dest.getParentFile().mkdir();
        }
        try {
            //后台上传
            files.transferTo(dest);
            return new ResponseResult(200,fileName);
        }catch (MaxUploadSizeExceededException e){
            e.printStackTrace();
            return new ResponseResult(400,"超过大小");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(400,"上传失败");
        }
    }
}
