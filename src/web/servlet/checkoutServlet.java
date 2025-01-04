package web.servlet;

import domain.*;
import persistence.ShopCartDao;
import persistence.impl.ShopCartDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class checkoutServlet extends HttpServlet {
    private static final String checkOut_FORM = "/WEB-INF/jsp/catalog/checkout.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        if(account!=null){
            ShopCart shopCart=services.checkOutServices.getShopCartInfo(req,resp);
            int orderId=services.checkOutServices.checkOutOperator(account.getUsername(),account.getAddress(),shopCart);

            if(shopCart!=null){
                ShopCartDao shopCartDao=new ShopCartDaoImpl();
                Order order=shopCartDao.getOrder(orderId);
                req.setAttribute("orders",order);
                req.getRequestDispatcher(checkOut_FORM).forward(req,resp);
            }else {
                resp.sendRedirect("shopCart");
            }
        }
        resp.sendRedirect("shopCart");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
