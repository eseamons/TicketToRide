package server.plugin;

import java.util.Set;

import shared.Result;

/**
 * Created by eseamons on 4/11/2017.
 */

public interface IAccountDao {

    Set<AccountDTO> getAll();

    Result addAccount(AccountDTO accountDTO);

    Result clearData();


}
