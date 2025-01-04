package Fliter;

import domain.Account;
import domain.Category;
import domain.ShopCart;
import persistence.impl.FilterDaoImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "RequestLoggingFilter", urlPatterns = "/*")
public class Filter01 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String msg = httpServletRequest.getParameter("msg");
        HttpSession session = httpServletRequest.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        if(account!=null){
            if(msg==null&& !(session.getAttribute("category") ==null)){
                String action = httpServletRequest.getParameter("action");
                Category category = (Category) session.getAttribute("category");
                String desc=account.getUsername()+"将"+category.name+"添加进购物车";
                if("add_to_cart".equals(action)){
                    try {
                        FilterDaoImpl.addToDataBase(account.getUsername(), desc,"add");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(msg!=null&&msg.equals("goCheckout")){
                ShopCart shopCart=services.checkOutServices.getShopCartInfo(httpServletRequest, (HttpServletResponse) servletResponse);
                String desc=account.getUsername()+"结账购物车"+shopCart.showInfo();
                try {
                    FilterDaoImpl.addToDataBase(account.getUsername(), desc,"checkout");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
