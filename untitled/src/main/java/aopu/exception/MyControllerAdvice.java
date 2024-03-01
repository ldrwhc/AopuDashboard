package aopu.exception;

import aopu.pojo.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseResult handlerException(Exception e){
        //获取异常信息，存放入ResponseResult的msg属性
        String message = e.getMessage();
        return new ResponseResult(300,message);
    }
}
