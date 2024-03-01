package aopu.controller;

import aopu.pojo.ResponseResult;
import aopu.pojo.User;
import aopu.service.UserService;
import aopu.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/page")
//    public String getPage(){
//        return "login";
//    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseResult login(@RequestBody User user, HttpServletResponse response) {
        // 校验用户名密码是否正确
        User loginUser = userService.login(user);
        Map<String, Object> map;
        if (loginUser != null) {
            map = new HashMap<>();
            // 如果正确生成token
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), String.valueOf(loginUser.getUsername()), null);
            map.put("token", token);
        } else {
            // 如果不正确给提示
            if(!userService.isExistByUsername(user.getUsername()))
                return new ResponseResult(401,"用户不存在");
            return new ResponseResult(401, "密码错误");
        }
        System.out.println(map.toString());
        response.setHeader("Access-Control-Expose-Headers","token");
        return new ResponseResult(200, "success", map);
    }

    //注册
    @PostMapping("/register")
    @CrossOrigin
    public ResponseResult register(@RequestBody User user){
        // 查询用户是否存在
        if (userService.isExistByUsername(user.getUsername()))
            return new ResponseResult(401,"用户名已经被占用");
        // 注册
        userService.register(user.getUsername(),user.getPassword(),user.getNickname(),user.getCorporateName(),user.getStoreName(),user.getTelephone());
        return new ResponseResult(200,"注册成功");
    }

    //查询用户信息
    @GetMapping("/userinfo")
    @CrossOrigin
    public ResponseResult<User> userInfo(@RequestHeader(name = "token")String token) throws Exception {
        //已登录用户则显示用户名
        Claims claims = JwtUtil.parseJWT(token);
        String username = claims.getSubject();
//        System.out.println("username"+username);
        User user = userService.findUserByUsername(username);
        System.out.println("userinfo" + user);
        return new ResponseResult<User>(200,user);
    }
//
    //修改用户信息
    @PostMapping("/update")
    @CrossOrigin
    public ResponseResult update(@RequestBody User user){
        System.out.println(user);
        userService.update(user);
        return new ResponseResult(200,"更新成功！");
    }
//
//    //修改密码
//    @PatchMapping("/updatePwd")
//    public ResponseResult updatePwd(@RequestBody Map<String,String> params,@RequestHeader(name = "token")String token){
//        //1.校验参数
//        String oldPwd = params.get("oldPwd");
//        String newPwd = params.get("newPwd");
//        String rePwd = params.get("rePwd");
//        if (oldPwd.isEmpty() && newPwd.isEmpty() && rePwd.isEmpty()){
//            return new ResponseResult(401,"，密码错误！");
//        }
//        if (newPwd.equals(oldPwd)){
//            return new ResponseResult(401,"两次输入的密码不一样！");
//        }
//        Claims claims = null;
//        try {
//            claims = JwtUtil.parseJWT(token);
//        } catch (Exception e) {
//            return new ResponseResult(401,"用户不存在！");
//        }
//        String username = (String) claims.getSubject();
//        System.out.println(username);
//        User user = userService.findUserByUsername(username);
//        //2.修改密码
//        userService.updatePwd(user,newPwd);
//        return new ResponseResult(200,"更新成功！");
//
//    }
}
