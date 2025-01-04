package web.servlet;

import domain.Account;
import domain.Category;
import domain.Goods;
import domain.ShopCart;
import persistence.ShopCartDao;
import persistence.impl.ShopCartDaoImpl;
import services.CatalogService;
import services.shopCartServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductFormServlet extends HttpServlet {
    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";
    private CatalogService catalogService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Account account= (Account) session.getAttribute("loginAccount");
        if(account ==null){
            response.sendRedirect("login");
        }
        else {
            if ("buy_now".equals(action))
            {
                // 立即购买逻辑
                response.sendRedirect("shopCart");
            }
            else if ("add_to_cart".equals(action))
            {
                // 调用插入商品到购物车的方法
                Integer itemCount = (Integer) session.getAttribute("itemCount");

                if (itemCount == null)
                {
                    itemCount = 0;
                }

                //insertGoods(request);
                Category category = (Category) session.getAttribute("category");

                account= (Account) session.getAttribute("loginAccount");
                String username=account.getUsername();
                ShopCartDao shopCartDao=new ShopCartDaoImpl();
                Goods goods=new Goods(0,category.name, category.desription, category.getImgURL(), category.price);
                boolean flag=shopCartDao.insertGoods(username,goods);
                itemCount=shopCartDao.getTotalRows(username);
                session.setAttribute("itemCount", itemCount);
                response.sendRedirect("product");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        if(id == null) {
            id = (String) session.getAttribute("id");
        }
        else {
            session.setAttribute("id",id);
        }
        catalogService = new CatalogService();
        List<Category> categoryList = catalogService.getCategoryList();
        int idAsInt = Integer.parseInt(id);
        String name = categoryList.get(idAsInt).name;

        System.out.println(name);

        Category category = catalogService.getCategory(name);


        session.setAttribute("category",category);
        session.setAttribute("categoryList",categoryList);

        req.getRequestDispatcher(PRODUCT_FORM).forward(req, resp);
    }
}
