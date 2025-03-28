package web.servlet;

import domain.Account;
import domain.ShopCart;
import persistence.ShopCartDao;
import persistence.impl.ShopCartDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class changeGoodsCountsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        int id= Integer.parseInt(req.getParameter("id"));
        int num= Integer.parseInt(req.getParameter("newNum"));
        ShopCartDao dao =new ShopCartDaoImpl();
        ShopCart shopcart=dao.findShopCart(account.getUsername());
        int TrueId=shopcart.getShopCarts().get(id).getKey().getId();
        System.out.println(id+" "+num);

        dao.changGoodsQuantity(TrueId,num);

    }
}
