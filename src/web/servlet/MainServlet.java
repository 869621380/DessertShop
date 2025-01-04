package web.servlet;

import domain.Account;
import domain.Category;
import persistence.ShopCartDao;
import persistence.impl.ShopCartDaoImpl;
import services.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";

    private CatalogService catalogService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        catalogService = new CatalogService();
        Category category = catalogService.getCategory(name);
        if(category != null)
        {
            session.setAttribute("id", category.categoryId);
            resp.sendRedirect("product");
        }
        else {
            req.getRequestDispatcher(MAIN_FORM).forward(req, resp);
        }
        // 处理结果并返回给客户端（可以重定向到显示结果的页面或者直接输出结果）

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        catalogService = new CatalogService();
        List<Category> categoryList = catalogService.getCategoryList();
        HttpSession session = req.getSession();
        session.setAttribute("categoryList",categoryList);

        Account account= (Account) session.getAttribute("loginAccount");
        if(account != null)
        {
            String username=account.getUsername();
            ShopCartDao shopCartDao=new ShopCartDaoImpl();
            Integer itemCount=shopCartDao.getTotalRows(username);
            session.setAttribute("itemCount", itemCount);
        }

        req.getRequestDispatcher(MAIN_FORM).forward(req, resp);
    }


}
