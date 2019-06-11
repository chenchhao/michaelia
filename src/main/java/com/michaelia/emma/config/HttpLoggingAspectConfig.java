package com.michaelia.emma.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
* <p>标题: HttpLoggingAspect.java</p>
* <p>业务描述:切面 </p>
* @date 2019年3月7日
* @version V1.0 
*/
@Component
@Aspect
@Slf4j
public class HttpLoggingAspectConfig {

	@Autowired

    private ObjectMapper mapper;



    @Pointcut("execution(public * com.michaelia.emma.web.*.controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数

    public void logPointCut() {

    }



    @Before("logPointCut()")

    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();



        // 记录下请求内容

        log.debug("┌──────────请求──────────────────");

        log.debug("┃控制器{}->方法{}-->参数{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), mapper.writeValueAsString(Arrays.toString(joinPoint.getArgs())));

        log.debug("┃{}-->{}-->{}", request.getRemoteAddr(), request.getMethod(), request.getRequestURL());

        log.debug("┃parameter{}", mapper.writeValueAsString(request.getParameterMap()));// 格式化输出的json mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        log.debug("┃body{}",getPayload(request));

        log.debug("└──────────────────────────────");



    }



    @Around("logPointCut()")

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object ob = pjp.proceed();// ob 为方法的返回值

        log.debug("┌──────────回复──────────────────");

        log.debug("┃耗时{}ms" ,(System.currentTimeMillis() - startTime));

        return ob;

    }



    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致

    public void doAfterReturning(Object ret) throws Throwable {

        log.debug("┃返回" + ret);

        log.debug("└──────────────────────────────");

    }



    private String getPayload(HttpServletRequest request) {

        ContentCachingRequestWrapper wrapper =  WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);

        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();

            if (buf.length > 0) {

                try {

                    int length = Math.min(buf.length, 1024);//最大只打印1024字节

                    return new String(buf, 0, length, wrapper.getCharacterEncoding());

                } catch (UnsupportedEncodingException var6) {

                    return "[unknown]";

                }

            }

        }

        return "";

    }
}
