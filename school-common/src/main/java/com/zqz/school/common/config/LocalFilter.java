package com.zqz.school.common.config;


import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: LocalFilter
 * @Date: Created in 17:23 2022-11-3
 */
@Configuration
public class LocalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.addHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "Accept,Authorization,DNT,Content-Type,Referer,User-Agent");
        response.addHeader("Access-Control-Allow-Credentials", "true"); // 允许携带验证信息
        filterChain.doFilter(req, response);
    }

}
