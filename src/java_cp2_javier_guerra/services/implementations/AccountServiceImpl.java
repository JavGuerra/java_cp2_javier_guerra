package java_cp2_javier_guerra.services.implementations;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.enums.AccountType;
import java_cp2_javier_guerra.entities.enums.CurrencyType;
import java_cp2_javier_guerra.repositories.AccountRepository;
import java_cp2_javier_guerra.services.IAccountService;

import java.util.*;

public class AccountServiceImpl implements IAccountService {

    private final Map<Long, Account> accounts = new AccountRepository(true).getAccounts();

    public Long newAccountId() {
        Long max = 0L;
        if (!accounts.isEmpty()) {
            for (Account account : accounts.values()) {
                Long accountId = account.getId();
                if (accountId > max) max = accountId;
            }
        }
        return ++max;
    }

    @Override
    public boolean accountExistById(long id) {
        if (id > 0) return accounts.containsKey(id);
        return false;
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
    public Optional<Account> getAccountById(long id) {
        if (id > 0) return Optional.ofNullable(accounts.get(id));
        return Optional.empty();
    }

    @Override
    public Optional<Account> getAccountByCustomerId(long id) {
        if (!accounts.isEmpty() && id > 0) {
            for (Account account : accounts.values())
                if (account.getIdCustomer().equals(id)) return Optional.of(account);
        }
        return Optional.empty();
    }

    @Override
    public List<Account> getAllAccountsByType(byte numType) {
        List<Account> listAccounts = new ArrayList<>();
        if (!accounts.isEmpty() && numType >= 0 && numType <= AccountType.values().length -1) {
            AccountType type = AccountType.values()[numType];
            for (Account account : accounts.values())
                if (account.getType() == type) listAccounts.add(account);
        }
      return listAccounts;
    }

    @Override
    public List<Account> getAllAccountsByCurrency(byte numType) {
        List<Account> listAccounts = new ArrayList<>();
        if (!accounts.isEmpty() && numType >= 0 && numType <= CurrencyType.values().length -1) {
            CurrencyType type = CurrencyType.values()[numType];
            for (Account account : accounts.values())
                if (currencyExistInAccount(account.getId(), numType)) listAccounts.add(account);
        }
        return listAccounts;
    }

    public boolean currencyExistInAccount(long id, byte numType) {
        boolean thereIsCurrency = false;
        if (!accounts.isEmpty() && id > 0 && numType >= 0 && numType <= CurrencyType.values().length - 1) {
            CurrencyType currencyType =  CurrencyType.values()[numType];
            Optional<Account> account = getAccountById(id);
            if (account.isPresent()) {
                for (CurrencyType type : account.get().getCurrencies()) {
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
    public Map<AccountType, List<Account>> getAccountTypesAndItsAccounts() {
        Map<AccountType, List<Account>> accountTypesAndItsAccounts = new HashMap<>();
        if (!accounts.isEmpty()) {
            List<Account> listAccountsByType;
            for (AccountType type : AccountType.values()) {
                listAccountsByType = getAllAccountsByType((byte) type.ordinal());
                if (!listAccountsByType.isEmpty()) accountTypesAndItsAccounts.put(type, listAccountsByType);
            }
        }
        return accountTypesAndItsAccounts;
    }

    @Override
    public boolean incrementOrDecrementAccountAmount(long id, double amount, boolean mode) {
        boolean completed = false;
        if (!accounts.isEmpty() && id > 0 && amount > 0) {
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
    public boolean transferAccountAmount(long id1, long id2, double amount) {
        boolean completed = false;
        if (!accounts.isEmpty() && id1 > 0 && id2 > 0 && amount > 0) {
            Optional<Account> account1 = getAccountById(id1);
            Optional<Account> account2 = getAccountById(id1);
            if (account1.isPresent() && account2.isPresent() && account1.get().getAmount() >= amount)
                if (incrementOrDecrementAccountAmount(id1, amount, false))
                    completed = incrementOrDecrementAccountAmount(id2, amount, true);
        }
        return completed;
    }

    @Override
    public boolean deleteAccount(long id) {
        return accounts.remove(id) != null;
    }
}
