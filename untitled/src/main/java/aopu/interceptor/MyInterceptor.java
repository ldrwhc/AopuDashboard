package aopu.interceptor;

import aopu.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {  //拦截器

    //在handler方法执行前会被调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //判断token是否为空，如果为空也代表未登录，提醒重新登录（401）
        String token = "";
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        token = request.getHeader("token");
        Claims claims = null;
//        if(!StringUtils.hasText(token)){
//            throw new RuntimeException("未登录，提醒重新登录");
//        }
        //解析token看看是否成功
        try{

            claims = JwtUtil.parseJWT(token);
            System.out.println(claims.getSubject());
            return true;
        }catch (Exception e){
            //如果解析过程中没有出现异常说明是登录状态
            //如果出现了异常，说明未登录，提醒重新登录
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException("未登录，提醒重新登录");
        }
    }

    public void postHandler(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        System.out.println("afterCompletion");
    }

}
