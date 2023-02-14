package java_cp2_javier_guerra.services.implementations;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.enums.AccountType;
import java_cp2_javier_guerra.entities.enums.CurrencyType;
import java_cp2_javier_guerra.repositories.AccountRepository;
import java_cp2_javier_guerra.services.IAccountService;

import java.util.*;

public class AccountServiceImpl implements IAccountService {

    private final Map<Long, Account> accounts = new AccountRepository(true).getAccounts();

    @Override
    public boolean accountExistById(Long id) {
        return accounts.containsKey(id);
    }

    @Override
    public Optional<Account> addAccount(Account account) {
        return Optional.ofNullable(accounts.put(account.getId(), account));
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public Optional<Account> getAccountByCustomerId(Long id) {
        if (!accounts.isEmpty() && id > 0) {
            for (Account account : accounts.values())
                if (account.getIdCustomer().equals(id)) return Optional.of(account);
        }
        return Optional.empty();
    }

    @Override
    public List<Account> getAllAccountsByType(byte numType) {
        List<Account> listAccounts =  new ArrayList<>();
        if (!accounts.isEmpty() && numType >= 1 && numType <= AccountType.values().length) {
            AccountType type = AccountType.values()[--numType];
            for (Account account : accounts.values())
                if (account.getType() == type) listAccounts.add(account);
        }
      return listAccounts;
    }

    @Override
    public List<Account> getAllAccountsByCurrency(byte numType) {
        List<Account> listAccounts =  new ArrayList<>();
        if (!accounts.isEmpty() && numType >= 1 && numType <= CurrencyType.values().length) {
            CurrencyType type = CurrencyType.values()[--numType];
            for (Account account : accounts.values()) {
                for (CurrencyType currency : account.getCurrencies()) {
                    if (currency == type) {
                        listAccounts.add(account);
                        break;
                    }
                }
            }
        }
        return listAccounts;
    }

    public boolean currencyExist(Long id, byte numType) {
        boolean thereIsCurrency = false;
        if (!accounts.isEmpty() && id > 0 && numType >= 0 && numType <= CurrencyType.values().length - 1) {
            CurrencyType currencyType =  CurrencyType.values()[numType];
            Optional<Account> optAccount = getAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                for (CurrencyType type : account.getCurrencies()) {
                    if (type == currencyType) {
                        thereIsCurrency = true;
                        break;
                    }
                }
            }
        }
        return thereIsCurrency;
    }

    @Override
    public Map<AccountType, Set<Account>> getAccountTypesAndItsAccounts() {
        Map<AccountType, Set<Account>> accountTypesAndItsAccounts = new HashMap<>();
        if (!accounts.isEmpty()) {
            List<Account> listAccounts = getAllAccounts();
            Set<Account> listAccountsByType;
            for (AccountType type : AccountType.values()) {
                listAccountsByType = new HashSet<>();
                for (Account account : listAccounts) {
                    if (account.getType() == type) listAccountsByType.add(account);
                }
                if (!listAccountsByType.isEmpty()) {
                    accountTypesAndItsAccounts.put(type, listAccountsByType);
                    // listAccounts = deleteAccountsFromAListByType(listAccounts, type);
                }
            }
        }
        return accountTypesAndItsAccounts;
    }

    @Override
    public List<Account> deleteAccountsFromAListByType(List<Account> listAccounts, AccountType accountType) {
        if (!listAccounts.isEmpty()) {
            for (Account account : listAccounts)
                if (account.getType() == accountType) listAccounts.remove(account);
        }
        return listAccounts;
    }

    @Override
    public boolean incrementOrDecrementAccountAmount(Long id, Double amount, boolean mode) {
        boolean completed = false;
        if (!accounts.isEmpty() && amount > 0) {
            Optional<Account> optAccount = getAccountById(id);

            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                if (mode) {
                    account.increaseAmount(amount);
                    completed = true;
                } else if (account.getAmount() >= amount) {
                    account.decrementAmount(amount);
                    completed = true;
                }
            }
        }
        return completed;
    }

    @Override
    public boolean transferAccountAmount(Long id1, Long id2, Double amount) {
        boolean transferred = false;
        if (!accounts.isEmpty() && amount > 0) {
            Optional<Account> account1 = getAccountById(id1);
            Optional<Account> account2 = getAccountById(id1);
            if (account1.isPresent() && account2.isPresent() && account1.get().getAmount() >= amount)
                if (incrementOrDecrementAccountAmount(id1, amount, false))
                    transferred = incrementOrDecrementAccountAmount(id2, amount, true);
        }
        return transferred;
    }

    @Override
    public boolean deleteAccount(Long id) {
        return accounts.remove(id) != null;
    }
}
