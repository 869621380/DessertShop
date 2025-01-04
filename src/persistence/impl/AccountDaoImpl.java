package persistence.impl;

import domain.Account;
import persistence.AccountDao;
import persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {

    Account account = null;

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?";

    private static final String GET_ACCOUNT_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

    private static final String INSERT_ACCOUNT = "INSERT INTO users(username, password, phone, email , adress) VALUES(?, ?, ?, ?, ?)";

    private static final String UPDATE_ACCOUNT = "UPDATE users SET phone = ?,email = ?,adress = ? WHERE username = ?";

    @Override
    public Account getAccountByUsername(Account account) throws Exception {
        Account accountResult = null;
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
        preparedStatement.setString(1, account.getUsername());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            accountResult = this.resultSetToAccount(resultSet);
        }
        DBUtil.closeConnection(connection);
        DBUtil.closeResultSet(resultSet);
        DBUtil.closeStatement(preparedStatement);
        return accountResult;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account accountResult = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                accountResult = this.resultSetToAccount(resultSet);
            }
            DBUtil.closeConnection(connection);
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountResult;
    }

    @Override
    public void insertAccount(Account account) throws Exception {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
        preparedStatement.setString(1, account.getUsername());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setString(3, account.getPhone());
        preparedStatement.setString(4, account.getEmail());
        preparedStatement.setString(5, account.getAddress());
        preparedStatement.executeUpdate();
    }

    @Override
    public void insertProfile(Account account) {

    }

    @Override
    public void insertLogin(Account account) {

    }

    @Override
    public void updateAccount(Account account) throws SQLException {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT);
        preparedStatement.setString(1, account.getPhone());
        preparedStatement.setString(2, account.getEmail());
        preparedStatement.setString(3, account.getAddress());
        preparedStatement.setString(4, account.getUsername());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateProfile(Account account) {

    }

    @Override
    public Account resultSetToAccount(ResultSet resultSet) throws SQLException {
        account = new Account();
        account.setUsername(resultSet.getString("username"));
        account.setPassword(resultSet.getString("password"));
        account.setAddress(resultSet.getString("adress"));
        account.setPhone(resultSet.getString("phone"));
        account.setEmail(resultSet.getString("email"));
        return account;
    }



//    public static void main(String[] args) {
//        Account account = new Account();
//        AccountDaoImpl accountDao = new AccountDaoImpl();
//        account.setUsername("111");
//        account.setPassword("111");
//        Account account2 = accountDao.getAccountByUsernameAndPassword(account);
//        System.out.println(account2.getPassword());
//    }
}
