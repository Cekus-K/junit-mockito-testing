package pl.cekus.testing.account;

import java.util.List;

interface AccountRepository {

    List<Account> getAllAccounts();
    List<String> getByName(String name);
}
