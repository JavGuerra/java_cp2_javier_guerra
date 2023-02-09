package java_cp2_javier_guerra.implementations;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.enums.BankAccountType;
import java_cp2_javier_guerra.interfaces.IAccountService;

import java.util.*;

public class AccountServiceImpl implements IAccountService {

    private final Map<Long, Account> accounts = new HashMap<>();

    @Override
    public boolean accountExist(Long id) {
        return accounts.containsKey(id);
    }

    @Override
    public void addAccount(Account account) {
        // TODO comprobar que se guarda
        accounts.put(account.getId(), account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Optional<Account> findAccountById(Long id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public Optional<Account> findAccountByCustomerId(Long id) {
        if (id > 0) {
            for (Account account : accounts.values())
                if (account.getIdCustomer().equals(id)) return Optional.of(account);
        }
        return Optional.empty();
    }

    @Override
    public List<Account> findAllAccountsByType(byte numType) {
        List<Account> listAccounts =  new ArrayList<>();
        if (numType >= 1 && numType <= BankAccountType.values().length) {
            BankAccountType type = BankAccountType.values()[--numType];
            for (Account account : accounts.values())
                if (account.getType() == type) listAccounts.add(account);
        }
      return listAccounts;
    }

    @Override
    public Account createAccount(String nif, BankAccountType type, Double amount) {
        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public boolean incrementAccountAmount(Long id, Double amount) {
        boolean incremented = false;
        if (amount > 0) {
            Optional<Account> optAccount = findAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                // TODO comprobar que se incrementa
                account.increaseAmount(amount);
                incremented = true;
            }
        }
        return incremented;
    }

    @Override
    public boolean decrementAccountAmount(Long id, Double amount) {
        boolean decremented = false;
        if (amount > 0) {
            Optional<Account> optAccount = findAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                if (account.getAmount() >= amount) {
                    // TODO comprobar que se decrementa
                    account.decrementAmount(amount);
                    decremented = true;
                }
            }
        }
        return decremented;
    }

    @Override
    public boolean transferAccountAmount(Long id1, Long id2, Double amount) {
        boolean transferred = false;
        if (amount > 0) {
            Optional<Account> account1 = findAccountById(id1);
            Optional<Account> account2 = findAccountById(id1);
            if (account1.isPresent() && account2.isPresent() && account1.get().getAmount() >= amount)
                // TODO revisar bien esto
                if (decrementAccountAmount(id1, amount)) transferred = incrementAccountAmount(id2, amount);
        }
        return transferred;
    }

    @Override
    public boolean deleteAccount(Long id) {
        return accounts.remove(id) != null;
    }
}
