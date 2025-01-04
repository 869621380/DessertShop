package services;

import domain.Account;
import persistence.AccountDao;
import persistence.impl.AccountDaoImpl;

import javax.servlet.http.HttpSession;

public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDaoImpl();
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }

    public Account getAccount(String username) throws Exception {
        Account account = new Account();
        account.setUsername(username);
        return accountDao.getAccountByUsername(account);
    }

    public boolean UpdateAccountInfo(String username,String email,String phone,String adress) throws Exception {
        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        account.setPhone(phone);
        account.setAddress(adress);
        accountDao.updateAccount(account);
        return true;
    }

    public boolean addAccount(String username, String password,String email,String phone,String adress) throws Exception {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setPhone(phone);
        account.setAddress(adress);
        if (accountDao.getAccountByUsername(account) != null) {
            return false;
        }else {
            accountDao.insertAccount(account);
        }
        return true;
    }

    public boolean verifyAccount(HttpSession session, String code) {
        if (code.equals((String) session.getAttribute("code"))) {
            return true;
        }else {
            return false;
        }
    }
}

