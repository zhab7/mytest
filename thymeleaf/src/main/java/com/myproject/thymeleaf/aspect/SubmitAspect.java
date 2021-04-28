package com.myproject.thymeleaf.aspect;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.myproject.thymeleaf.exception.ProjectEnum;
import com.myproject.thymeleaf.exception.ProjectGlobalException;
import com.myproject.thymeleaf.model.annotation.RepeatSubmit;
import com.myproject.thymeleaf.model.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 避免短时间内重复提交问题
 */
@Aspect
@Slf4j
@Configuration
public class SubmitAspect {
    private final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 100 个
            .maximumSize(100)
            // 设置缓存过期时间为S
            .expireAfterWrite(500, TimeUnit.MILLISECONDS)
            .build();

    @Pointcut("@annotation(com.myproject.thymeleaf.model.annotation.RepeatSubmit)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object interceptor(ProceedingJoinPoint joinPoint) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            log.info("用户未登录。");
            return null;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RepeatSubmit form = method.getAnnotation(RepeatSubmit.class);
        String key = getCacheKey(user.getId(), method, joinPoint.getArgs());
        if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) == null) {
                // 如果是第一次请求,就将key存入缓存中
                CACHES.put(key, key);
            } else {
//                ResultEntity resultResponse = new ResultEntity();
//                resultResponse.setData(null);
//                resultResponse.setMsg("请勿重复请求");
//                resultResponse.setCode(405);
//                return resultResponse;

                log.info("请勿重复请求。");
                throw new ProjectGlobalException(ProjectEnum.REPEAT_SUBMIT);
            }
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        }
    }

    /**
     * 加上用户的唯一标识
     */
    private String getCacheKey(Long uid, Method method, Object[] args) {
        int length = 0;
        if (args != null && args.length > 0) {
            length = args.length;
        }
        log.info("请求参数长度 = {}", length);
        return uid + "/" + method.getName() + length;
    }
}
