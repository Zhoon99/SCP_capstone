package kr.mmgg.scp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kr.mmgg.scp.config.AppProperties;
import kr.mmgg.scp.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("Interceptor test : " + request.getRequestURI());

        System.out.println(request.getAttribute("userId"));

        return true;
    }
}
