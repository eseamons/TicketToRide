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

    //Maps
    Map<String, Account> accountAuthMap;
    Map<String, Account> accountUsernameMap;

    public AccountList() {
        accounts = new ArrayList<>();
        accountAuthMap = new HashMap<>();
        accountUsernameMap = new HashMap<>();
    }

    public boolean registerAccount(String name, String pass) {
        Account newAccount = new Account();
        newAccount.setUsername(name);
        newAccount.setPassword(pass);
        accounts.add(newAccount);

        //create authentication code
        String authCode = UUID.randomUUID().toString();
        newAccount.setAuthentication(authCode);


        //Map authcode to Account for quicker lookup
        accountAuthMap.put(authCode, newAccount);
        //Map username to Account for quicker lookup
        accountUsernameMap.put(name, newAccount);

        return true;
    }

    public Account login(String name, String pass) {
        Account loginAccount = null;
        for (Account account : accounts) {
            if(account.getPassword().equals(pass) && account.getUsername().equals(name)) {
                loginAccount = account;
            }
        }
        return loginAccount;
    }

    public boolean authCodeExists(String auth) {
        // find auth code in hashmap
        return accountAuthMap.containsKey(auth);
    }

    public boolean usernameExists(String username) {
        //find username in hashmap
        return accountUsernameMap.containsKey(username);
    }

    public Account getAccountByAuthCode(String auth) {
        return accountAuthMap.get(auth);
    }



}
