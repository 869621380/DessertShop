package org.example.dessertshopspringboot.Filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String clientIP = httpRequest.getRemoteAddr();
        String method = httpRequest.getMethod(); // 获取请求方法


        logger.info("用户请求路径: {}, 客户端IP: {}, 请求方法: {}", requestURI, clientIP, method);
        // 继续处理请求
        chain.doFilter(request, response);
    }


}