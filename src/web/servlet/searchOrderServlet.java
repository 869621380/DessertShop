package web.servlet;

import domain.Account;
import domain.Order;
import persistence.impl.ShopCartDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class searchOrderServlet extends HttpServlet {
    private static final String USER_ORDER_FORM = "/WEB-INF/jsp/catalog/userOrder.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Account account= (Account) session.getAttribute("loginAccount");
        if(account!=null){
            ArrayList<Order>allOrder=new ArrayList<>();
           persistence.ShopCartDao shopCartDao=new ShopCartDaoImpl();
           ArrayList<Integer>arrayList=shopCartDao.findAllOrderId(account.getUsername());
           for(int i=0;i<arrayList.size();++i)
               allOrder.add(shopCartDao.getOrder(arrayList.get(i)));
            Collections.reverse(allOrder);
            req.setAttribute("AllOrder", allOrder);
            req.getRequestDispatcher(USER_ORDER_FORM).forward(req,resp);
        }else {
            resp.sendRedirect("login");
        }
    }
}
