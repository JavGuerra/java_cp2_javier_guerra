package java_cp2_javier_guerra.implementations;

import java_cp2_javier_guerra.entities.BankAccount;
import java_cp2_javier_guerra.enums.BankAccountType;
import java_cp2_javier_guerra.interfaces.IBankAccountService;

import java.util.*;

public class BankAccountServiceImpl implements IBankAccountService {

    private final Set<BankAccount> accounts = new HashSet<>();

    public Boolean addAccount(BankAccount account) {
        return accounts.add(account);
    }

    @Override
    public List<BankAccount> findAll() {
        return new ArrayList<>(accounts);
    }

    @Override
    public BankAccount findById(Long id) {
        if (id > 0)
            for (BankAccount account : accounts)
                if (id.equals(account.getId())) return account;
        return null;
    }

    @Override
    public BankAccount findByNif(String nif) {
        if (nif != null && !nif.equals(""))
            for (BankAccount account : accounts)
                if (nif.equals(account.getUserNif())) return account;
        return null;
    }

    public List<BankAccount> findAllByType(byte numType) {
        List<BankAccount> listAccounts =  new ArrayList<>();
        if (numType >= 1 && numType <= 3) {
            BankAccountType type = BankAccountType.values()[--numType];
            for (BankAccount account : accounts) if (account.getType() == type) listAccounts.add(account);
        }
      return listAccounts;
    }

    @Override
    public BankAccount create(String nif, BankAccountType type, Double amount) {
        return null;
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        return null;
    }

    @Override
    public boolean incrementAmount(Long id, Double amount) {
        Optional<BankAccount> optAccount = Optional.ofNullable(findById(id));
        if (optAccount.isPresent() && amount > 0) {
            BankAccount account = optAccount.get();
            account.increaseAmount(amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean decrementAmount(Long id, Double amount) {
        if (amount > 0) {
            Optional<BankAccount> optAccount = Optional.ofNullable(findById(id));
            if (optAccount.isPresent()) {
                BankAccount account = optAccount.get();
                if (account.getAmount() >= amount) {
                    account.decrementAmount(amount);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean transferAmount(Long id1, Long id2, Double amount) {
        if (amount > 0) {
            Optional<BankAccount> account1 = Optional.ofNullable(findById(id1));
            Optional<BankAccount> account2 = Optional.ofNullable(findById(id1));
            if (account1.isPresent() && account2.isPresent() && account1.get().getAmount() >= amount)
                if (decrementAmount(id1, amount)) return incrementAmount(id2, amount);
        }
        return false;
    }

    @Override
    public boolean deleteAccount(Long id) {
        Optional<BankAccount> account = Optional.ofNullable(findById(id));
        return account.filter(accounts::remove).isPresent();
    }
}
