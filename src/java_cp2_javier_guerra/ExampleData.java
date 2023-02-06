package java_cp2_javier_guerra;

import java_cp2_javier_guerra.entities.BankAccount;
import java_cp2_javier_guerra.enums.BankAccountType;
import java_cp2_javier_guerra.implementations.BankAccountServiceImpl;
import java_cp2_javier_guerra.interfaces.IBankAccountService;

public class ExampleData {

    public static IBankAccountService exampleBankAccountService() {
        var accountService = new BankAccountServiceImpl();

        accountService.addAccount(new BankAccount(1L, 50000d, BankAccountType.BUSINESS, 50d, "12345678A",2012));
        accountService.addAccount(new BankAccount(2L, 5000d, BankAccountType.INVESTMENT, 25d, "12345678B",2010));
        accountService.addAccount(new BankAccount(3L, 50000d, BankAccountType.BUSINESS, 50d, "12345678C",2018));

        return accountService;
    }
}
