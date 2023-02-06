package java_cp2_javier_guerra.implementations;

import java_cp2_javier_guerra.entities.BankAccount;
import java_cp2_javier_guerra.enums.BankAccountType;
import java_cp2_javier_guerra.interfaces.IBankAccountService;

import java.util.*;

public class BankAccountServiceImpl implements IBankAccountService {

    private final Map<Long, BankAccount> accounts = new HashMap<>();
    // private final Set<BankAccount> accounts = new HashSet<>();

    public Optional<BankAccount> addAccount(BankAccount account) {
        return Optional.ofNullable(accounts.put(account.getId(), account));
    }

    @Override
    public List<BankAccount> findAll() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public Optional<BankAccount> findByNif(String nif) {
        if (nif != null && !nif.equals(""))
            for (BankAccount account : accounts.values())
                if (nif.equals(account.getUserNif())) return Optional.of(account);
        return Optional.empty();
    }

    public List<BankAccount> findAllByType(byte numType) {
        List<BankAccount> listAccounts =  new ArrayList<>();
        if (numType >= 1 && numType <= BankAccountType.values().length) {
            BankAccountType type = BankAccountType.values()[--numType];
            for (BankAccount account : accounts.values())
                if (account.getType() == type) listAccounts.add(account);
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
        boolean incremented = false;
        if (amount > 0) {
            Optional<BankAccount> optAccount = findById(id);
            if (optAccount.isPresent()) {
                BankAccount account = optAccount.get();
                // TODO comprobar que se incrementa
                account.increaseAmount(amount);
                incremented = true;
            }
        }
        return incremented;
    }

    @Override
    public boolean decrementAmount(Long id, Double amount) {
        boolean decremented = false;
        if (amount > 0) {
            Optional<BankAccount> optAccount = findById(id);
            if (optAccount.isPresent()) {
                BankAccount account = optAccount.get();
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
    public boolean transferAmount(Long id1, Long id2, Double amount) {
        boolean transferred = false;
        if (amount > 0) {
            Optional<BankAccount> account1 = findById(id1);
            Optional<BankAccount> account2 = findById(id1);
            if (account1.isPresent() && account2.isPresent() && account1.get().getAmount() >= amount)
                // TODO revisar bien esto
                if (decrementAmount(id1, amount)) transferred = incrementAmount(id2, amount);
        }
        return transferred;
    }

    @Override
    public boolean deleteAccount(Long id) {
        return accounts.remove(id) != null;
    }
}
