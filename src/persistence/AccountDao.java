package persistence;

import domain.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AccountDao {
    Account getAccountByUsername(Account account) throws Exception;
    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account) throws Exception;

    void insertProfile(Account account);

    void insertLogin(Account account);

    void updateAccount(Account account) throws SQLException;

    void updateProfile(Account account);

    Account resultSetToAccount(ResultSet resultSet) throws SQLException;

}
