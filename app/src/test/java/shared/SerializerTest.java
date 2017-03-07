package shared;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shared.command_classes.Command;
import shared.model_classes.Account;

import static org.junit.Assert.*;

/**
 * Created by erics on 3/7/2017.
 */
public class SerializerTest {
    @Test
    public void serialize() throws Exception {

        Account account = new Account();
        account.setUsername("eseamons");
        account.setPassword("mypass");
        account.setAuthentication("ajdk-3428-dfdk-234897");

        Result r = new Result(true, account);
        String json = Serializer.serialize(r);
        Result result = (Result) Serializer.deserialize(json);
        Account newAccount = (Account) result.getInfo();
        System.out.println("Done");
    }


    @Test
    public void serializeList() throws Exception {

        Account account = new Account();
        account.setUsername("eseamons");
        account.setPassword("mypass");
        account.setAuthentication("ajdk-3428-dfdk-234897");

        Account accountone = new Account();
        accountone.setUsername("ale");
        accountone.setPassword("alepass");
        accountone.setAuthentication("urie-3847-ilpa-6372");

        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        accountList.add(accountone);

        String jsonList = Serializer.serialize(accountList.toArray());
        Object[] objArray = Serializer.deserializeList(jsonList);

        for(Object obj : objArray) {
            Account tempAccount = (Account) obj;
            System.out.println(tempAccount.getUsername());
        }
    }

}