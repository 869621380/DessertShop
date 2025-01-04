package web.servlet;

import domain.Account;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private static final String REGIST_FORM = "/WEB-INF/jsp/account/regist.jsp";

    private static final String LOGIN_FORM = "login";

    private String username;
    private String password;
    private String email;
    private String phone;
    private String adress;
    private String code;
    private String RegistMsg;
    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        this.session = req.getSession();
        AccountService accountService = new AccountService();
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        this.email = req.getParameter("email");
        this.phone = req.getParameter("phone");
        this.adress = req.getParameter("adress");
        this.code = req.getParameter("code");

        if(!validate(accountService)){
            req.setAttribute("RegistMsg", RegistMsg);
            req.getRequestDispatcher(REGIST_FORM).forward(req, resp);
        }else {
            try {
                if(!accountService.addAccount(username,password,email,phone,adress)){
                    this.RegistMsg = "用户名已被占用！";
                    req.setAttribute("RegistMsg", RegistMsg);
                    req.getRequestDispatcher(REGIST_FORM).forward(req, resp);
                }else {
                    req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGIST_FORM).forward(req, resp);
    }

    private boolean validate(AccountService accountService) {
        if (!accountService.verifyAccount(session,code)){
            this.RegistMsg = "验证码输入不正确！";
            return false;
        }
        if (this.username == null || this.username.equals("")) {
            this.RegistMsg = "用户名不能为空！";
            return false;
        }
        if (this.password == null || this.password.equals("")) {
            this.RegistMsg = "密码不能为空！";
            return false;
        }
        if (this.email == null || this.email.equals("")) {
            this.RegistMsg = "请输入email！";
            return false;
        }
        if (this.phone == null || this.phone.equals("")) {
            this.RegistMsg = "请输入手机号！";
            return false;
        }
        return true;
    }
}
