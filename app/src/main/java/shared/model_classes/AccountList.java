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

    //List of user accounts
    private List<Account> accounts;
    //Maps authentication code to Account for quicker lookup
    Map<String, Account> accountAuthMap;
    //Maps username to Account for quicker lookup
    Map<String, Account> accountUsernameMap;

    public AccountList() {
        accounts = new ArrayList<>();
        accountAuthMap = new HashMap<>();
        accountUsernameMap = new HashMap<>();
    }

    /**
     * Function registers a new account in the system
     * @param username
     * @param password
     * @return boolean for whether account was successfully registered
     */
    public boolean registerAccount(String username, String password) {

        if (!usernameExists(username)) {
            //create new Account
            Account newAccount = new Account();
            newAccount.setUsername(username);
            newAccount.setPassword(password);
            accounts.add(newAccount);

            //create authentication code
            String authCode = UUID.randomUUID().toString();
            newAccount.setAuthentication(authCode);

            //map authorization code and username to Account
            accountAuthMap.put(authCode, newAccount);
            accountUsernameMap.put(username, newAccount);
        }

        return true;
    }

    /**
     * This function logs a user into the system
     * @param username username of user
     * @param password password of user
     * @return boolean indicating if register action was successful
     */
    public Account login(String username, String password) {
        Account loginAccount = null;
        for (Account account : accounts) {
            if(account.loginInfoValid(username, password)) {
                loginAccount = account;
            }
        }
        return loginAccount;
    }

    public boolean authCodeExists(String auth) {
        // find auth code in hashmap
        return accountAuthMap.containsKey(auth);
    }

    /**
     * Checks if username exists. This function helps us ensure that two accounts don't use
     * the same username
     * @param username
     * @return boolean for whether username exists
     */
    private boolean usernameExists(String username) {
        //find username in hashmap
        return accountUsernameMap.containsKey(username);
    }

    /**
     * Gets Account based on authcode
     * @param auth
     * @return Account for a given authcode
     */
    public Account getAccountByAuthCode(String auth) {
        return accountAuthMap.get(auth);
    }



}
