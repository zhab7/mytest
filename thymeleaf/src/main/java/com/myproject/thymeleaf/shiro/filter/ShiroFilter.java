package com.myproject.thymeleaf.shiro.filter;

import com.myproject.thymeleaf.shiro.authentication.JWTToken;
import com.myproject.thymeleaf.shiro.config.ShiroSessionDAO;
import com.myproject.thymeleaf.shiro.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ThomasYu on 2019-08-05
 */
@Slf4j
public class ShiroFilter extends AccessControlFilter {

    private ShiroSessionDAO shiroSessionDAO;
    private MessageSource i18nMessageSource;

    public ShiroFilter(ShiroSessionDAO shiroSessionDAO, MessageSource i18nMessageSource) {
        this.shiroSessionDAO = shiroSessionDAO;
        this.i18nMessageSource = i18nMessageSource;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        }
        Subject subject = getSubject(request, response);
        // If principal is not null, then the user is known and should be allowed access.
        if (subject.getPrincipal() != null) {
            return true;
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(CommonConstant.HEADER_X_ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        JWTToken gsSimpleToken = new JWTToken(token);
        try {
            subject.login(gsSimpleToken);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
/*        RestResp restResp = new RestResp();
        restResp.setCode(SysErrorEnum.TOKEN_INVALID.getCode());
        restResp.setMessage(SysErrorEnum.TOKEN_INVALID.getMsg());
        restResp.setTraceId(MDC.get(CommonConstant.TRACE_KEY));

        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.getWriter().write(JsonUtils.json.writeValueAsString(restResp));*/

//        saveRequestAndRedirectToLogin(request, response);
        log.debug("onAccessDenied called");
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        super.afterCompletion(request, response, exception);
        shiroSessionDAO.clearSessionInMem();
    }
}
