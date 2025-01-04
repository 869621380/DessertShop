package web.servlet;

import com.mysql.cj.Session;
import domain.Account;
import domain.ShopCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import services.shopCartServices;
public class shopCartServlet extends HttpServlet {

    private static final String ShopCart_FORM = "/WEB-INF/jsp/catalog/shop-cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Account account= (Account) session.getAttribute("loginAccount");
        if(account!=null){
            ShopCart shopCart=shopCartServices.getShopCartMsg(account.getUsername());
            req.setAttribute("shopCart", shopCart);
            req.getRequestDispatcher(ShopCart_FORM).forward(req,resp);
        }else {
            resp.sendRedirect("login");
        }

    }
}
