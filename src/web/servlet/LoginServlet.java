package web.servlet;

import domain.Account;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final String LOGIN_FORM = "/WEB-INF/jsp/account/login.jsp";
    private static final String REGISTER_FORM = "register";
    private static final String MAIN_FORM = "main";

    private String username;
    private String password;
    private String loginMsg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求编码
        req.setCharacterEncoding("UTF-8");

        this.username = req.getParameter("username");
        this.password = req.getParameter("password");

        if(!validate()){
            req.setAttribute("loginMsg", loginMsg);
            req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
        }else {
            AccountService accountService = new AccountService();
            Account loginAccount = accountService.getAccount(this.username, this.password);
            if(loginAccount == null){
                this.loginMsg = "用户名或密码错误！";
                req.setAttribute("loginMsg", loginMsg);
                req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("loginAccount", loginAccount);
                session.setAttribute("user_name", loginAccount.getUsername());
                resp.sendRedirect(MAIN_FORM);    //这里跳转到首页！！！！
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
    }

    private boolean validate() {
        if (this.username == null || this.username.equals("")) {
            this.loginMsg = "用户名不能为空！";
            return false;
        }
        if (this.password == null || this.password.equals("")) {
            this.loginMsg = "密码不能为空！";
            return false;
        }
        return true;
    }
}
