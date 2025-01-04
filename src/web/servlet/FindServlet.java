package web.servlet;

import domain.Account;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FindServlet extends HttpServlet {

    private static final String FIND_FORM = "/WEB-INF/jsp/account/find_password.jsp";

    private static final String MAIN_FORM = "main";


    private String username;
    private String email;
    private String phone;
    private String adress;
    private String code;
    private String FindMsg;
    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        this.session = req.getSession();
        AccountService accountService = new AccountService();
        this.username = session.getAttribute("user_name").toString();
        this.email = req.getParameter("email");
        this.phone = req.getParameter("phone");
        this.adress = req.getParameter("adress");

        if(!validate(accountService)){
            req.setAttribute("FindMsg", FindMsg);
            req.getRequestDispatcher(FIND_FORM).forward(req, resp);
        }else {
            try {

                accountService.UpdateAccountInfo(username,email,phone,adress);
                Account loginAccount = accountService.getAccount(username);
                session.setAttribute("loginAccount", loginAccount);
                resp.sendRedirect(MAIN_FORM);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(FIND_FORM).forward(req, resp);
    }

    private boolean validate(AccountService accountService) {
        if (this.username == null || this.username.equals("")) {
            this.FindMsg = "请先登录！";
            return false;
        }
        if (this.email == null || this.email.equals("")) {
            this.FindMsg = "请输入email！";
            return false;
        }
        if (this.phone == null || this.phone.equals("")) {
            this.FindMsg = "请输入手机号！";
            return false;
        }
        if (this.adress == null || this.adress.equals("")) {
            this.FindMsg = "请输入收货地址！";
            return false;
        }
        return true;
    }
}
