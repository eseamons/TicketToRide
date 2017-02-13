package shared.model_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by erics on 2/13/2017.
 */

public class AccountList {

    private List<Account> accounts;
    Map<String, Account> accountAuthMap;

    public AccountList() {
        accounts = new ArrayList<>();
        accountAuthMap = new HashMap<>();
    }

    public Account login(String name, String pass) {
        Account loginAccount = null;
        for (Account account : accounts) {
            if(account.getPassword() == pass && account.getUsername() == name) {
                loginAccount = account;
            }
        }
        return loginAccount;
    }

    public boolean isAuthCodeValid(String auth) {
        boolean isAuthValid = false;
        for (Account account : accounts) {
            if(account.getAuthentication() == auth) {
                isAuthValid = true;
            }
        }
        return isAuthValid;
    }

    public boolean doesAccountExist(String name) {
        boolean accountExists = false;
        for (Account account : accounts) {
            if(account.getUsername() == name) {
                accountExists = true;
            }
        }
        return accountExists;
    }

    public boolean createAccount(String name, String pass) {
        Account newAccount = new Account();
        newAccount.setUsername(name);
        newAccount.setPassword(pass);
        accounts.add(newAccount);

        //create authentication code
        String uuid = UUID.randomUUID().toString();
        newAccount.setAuthentication(uuid);
        accountAuthMap.put(uuid, newAccount);
        return true;
    }



}
