package com.malefeng.mychat.config.exception;

import com.malefeng.mychat.bean.dto.response.Response;
import com.malefeng.mychat.bean.enums.RuntimeErrorEnum;
import com.malefeng.mychat.bean.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName MyExceptionHandler
 * @Description 统一异常处理
 * @Author malf
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e   自定义异常
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response myExceptionHandler(HttpServletRequest req, MyException e) {
        log.error("MyException error msg：{}", e.getErrorMsg());
        return new Response<>().error(e.getCode(), e.getErrorMsg());
    }

    /**
     * 处理实体参数校验异常 (spring validation)
     *
     * @param req
     * @param e   spring validation 抛出的异常
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 这里列出了全部错误参数，这里用List传回
        List<String> fieldErrorMsg = new ArrayList<>();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                errors.forEach(msg -> fieldErrorMsg.add(msg.getDefaultMessage()));
                return new Response<>().error(RuntimeErrorEnum.BAD_REQUEST.getCode(), StringUtils.collectionToDelimitedString(fieldErrorMsg,";"));
            }
        }
        log.warn("request parameter error:", e);
        return new Response<>(RuntimeErrorEnum.BAD_REQUEST);
    }

    /**
     * @Author malf
     * @Description 校验拼接参数
     * @Date 14:57 2021/1/7
     *
     * @Param [exception]
     * @return com.innovent.authority.pojo.dto.response.Response
     **/
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response exceptionhandle(ValidationException exception){
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            String msg = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
            return new Response().error(RuntimeErrorEnum.BAD_REQUEST.getCode(),msg);
        }
        return new Response<>(RuntimeErrorEnum.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response authenticationException(AuthenticationException e){
        return new Response<>(RuntimeErrorEnum.UN_AUTH);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response exceptionHandler( Exception e) {
        log.info("【error msg】",e);
        return new Response(RuntimeErrorEnum.ERROR);
    }
}
