package com.michaelia.emma.common;

import com.michaelia.emma.enums.GlobleEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionHandle {
	
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Response<?> Handle(Exception e){
        if (e instanceof BusinessException){
        	BusinessException be = (BusinessException) e;
            return ResponseUtil.fail(be.getCode(),be.getMessage());
        }else if(e instanceof BindException){
        	BindException be = (BindException) e;
        	BindingResult bindingResult = be.getBindingResult();
        	StringBuffer sb = new StringBuffer();
        	int current=1;
            for(FieldError fieldError :bindingResult.getFieldErrors()){
            	if(current<bindingResult.getFieldErrors().size()) {
            		sb.append(fieldError.getDefaultMessage()+"/");
            	}else {
            		sb.append(fieldError.getDefaultMessage());
            	}
            	current++;
            }
        	return ResponseUtil.fail(GlobleEnum.SYS_VALIDATE_ERROR.getCode(),sb.toString());
        }else if(e instanceof UnauthorizedException){
        	return ResponseUtil.fail(GlobleEnum.SYS_UNAUTHORIZED.getCode(),GlobleEnum.SYS_UNAUTHORIZED.getMessage());
        }else {
            log.info("[=======系统异常======]{}",e);
            return ResponseUtil.fail(GlobleEnum.SYS_SYSTEM_ERROR.getCode(),GlobleEnum.SYS_SYSTEM_ERROR.getMessage());
        }
    }
}
