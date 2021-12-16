package com.xlh.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xlh.common.base.Result;
import com.xlh.common.base.ResultCode;
import com.xlh.expection.BeanValidationException;
import com.xlh.expection.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * 业务异常
     * @param businessException
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result BusinessException(BusinessException businessException){
        log.error("错误信息：", businessException);
        return Result.fail(businessException.getCode(), businessException.getMessage());
    }

    /**
     * 新文件异常
     * @param accessDeniedException
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result AccessDeniedException(AccessDeniedException accessDeniedException){
        log.error("错误信息：", accessDeniedException);
        return Result.fail(ResultCode.FEILE_NOT_FOUND.getCode(), ResultCode.FEILE_NOT_FOUND.getMsg());
    }


    /**
     * 接口参数异常自定义
     * @param exs
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result MethodArgumentNotValidException(MethodArgumentNotValidException exs){
        log.error("错误信息：", exs);
        BindingResult bindingResult = exs.getBindingResult();
        StringBuffer sb = new StringBuffer();
        for(FieldError fieldError :bindingResult.getFieldErrors()){
            sb.append("|").append(fieldError.getDefaultMessage());
        }
        return Result.fail(ResultCode.PARAM_VALID_ERROR.getCode(), sb.substring(1));
    }

    /**
     * 参数校验不通过异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(BeanValidationException.class)
    @ResponseBody
    public Result BeanValidationException(BeanValidationException e){
        log.error("错误信息：", e);
        return e.getResult();
    }

    /**
     * 对象校验不通过异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result ConstraintViolationException(ConstraintViolationException exception){
        log.error("错误信息：", exception);
        String errorMsgs = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).sorted().collect(Collectors.joining(";"));
        return Result.builder()
                .code(ResultCode.PARAM_VALID_ERROR.getCode())
                .msg(errorMsgs)
                .build();
    }

    /**
     * 时间类异常
     * @param exception
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Result DataIntegrityViolationException(DataIntegrityViolationException exception){
        log.error("错误信息：", exception);
        return Result.fail(ResultCode.DATA_INTERGRITY_VIOLATION_EXCEPTION.getCode(), ResultCode.DATA_INTERGRITY_VIOLATION_EXCEPTION.getMsg()+"(异常拦截)");
    }

    /**
     * 时间类异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Result DataIntegrityViolationException(MethodArgumentTypeMismatchException exception){
        log.error("错误信息：", exception);
        return Result.fail(ResultCode.PARAMS_CONVERSION_EXCEPTION.getCode(),
                ResultCode.PARAMS_CONVERSION_EXCEPTION.getMsg()+":"+exception.getName()+/*"->"+exception.getMessage()+*/"(异常拦截)");
    }

    /**
     * springFrameworkException
     * @param e
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void springFrameworkException(Exception e, HttpServletRequest request, ServletResponse response) throws Exception {
        log.error("错误信息：", e);
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String message = e.getMessage();
        if(StringUtils.isNotBlank(message)){
            int indexof = message.indexOf(":");
            if(indexof > -1){
                message = message.substring(0,indexof).replace("Cannot deserialize value of type","");
            }

        }
        String json = JSONObject.toJSONString(Result.fail(ResultCode.PARAMS_CONVERSION_EXCEPTION.getMsg()+"->"+message), SerializerFeature.WriteMapNullValue);
        servletOutputStream.write(json.getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        log.error("错误信息：", e);
        return Result.fail("网络繁忙,请稍后再试");
    }

}
