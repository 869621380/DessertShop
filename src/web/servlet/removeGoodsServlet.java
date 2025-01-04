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

public class removeGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        int id= Integer.parseInt(req.getParameter("id"));

        ShopCartDao dao =new ShopCartDaoImpl();
        ShopCart shopcart=dao.findShopCart(account.getUsername());
        int TrueId=shopcart.getShopCarts().get(id).getKey().getId();
        System.out.println(id);

        dao.deleteGoods(TrueId);
        resp.setContentType("text/plain");
        // 获取输出流对象
        PrintWriter out = resp.getWriter();
        // 写入要返回的内容，这里可以根据需要自定义文本内容，此处简单写个示例文本
        out.write("操作已完成");

    }
}
