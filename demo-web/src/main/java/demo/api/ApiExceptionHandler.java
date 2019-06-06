package demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API:异常处理器
 *
 * */
@RestControllerAdvice(annotations = {RestController.class})
public class ApiExceptionHandler {

    //https://blog.csdn.net/connie1451/article/details/85336481
    /**
     * 请求参数异常
     * */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String badRequestBindException(BindException exception) {
        System.out.println("请求参数异常了.....");
        return "请求参数异常了";
    }
}
