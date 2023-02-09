package java_cp2_javier_guerra;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.Customer;
import java_cp2_javier_guerra.enums.BankAccountType;
import java_cp2_javier_guerra.interfaces.*;

import java.util.List;
import java.util.Optional;

import static java_cp2_javier_guerra.ExampleData.exampleBank;
import static java_cp2_javier_guerra.enums.BankAccountType.getBankAccountTypeList;
import static java_cp2_javier_guerra.utils.ConsoleInput.*;


public abstract class MenuOptions {

    private static final IAccountService accountService = (IAccountService) exampleBank().getService("accountService");
    private static final ICustomerService customerService = (ICustomerService) exampleBank().getService("customerService");
    private static final IEmployeeService employeeService = (IEmployeeService) exampleBank().getService("employeeService");
    private static final IAddressService addressService = (IAddressService) exampleBank().getService("addressService");


    /**
     *
     */
    public static void showAccountList() {
        title("Listar todas las cuentas");

        List<Account> accounts = accountService.getAllAccounts();
        if (!accounts.isEmpty()) {
            accounts.forEach(System.out::println);
            System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
        } else System.out.println("No se han encontrado cuentas bancarias.");
    }


    /**
     *
     */
    public static void showAccountById() {
        title("Buscar una cuenta por su id");

        Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
        Optional<Account> account = accountService.findAccountById(id);
        System.out.println(account.isPresent() ? account.get() : "No se ha encontrado la cuenta.");
    }

    /**
     *
     */
    public static void showAccountByUserNIF() {
        title("Buscar una cuenta por el NIF del usuario");

        String nif = getWord("Introduzca el NIF del usuario: ");
        Optional<Customer> optCustomer = customerService.findCustomerByNif(nif);
        if (optCustomer.isPresent()) {
            Optional<Account> account = accountService.findAccountByCustomerId(optCustomer.get().getId());
            System.out.println(account.isPresent() ? account.get() : "No se ha encontrado la cuenta.");
        } else System.out.println("No se ha encontrado el usuario.");
    }

    /**
     *
     */
    public static void showAccountsByType() {
        title("Buscar las cuentas por el tipo de cuenta");

        String msg = "Elija tipo:" + getBankAccountTypeList() + ": ";
        byte pos = getLongIntPosByRange(msg, 1L, (long) BankAccountType.values().length).byteValue();
        List<Account> accounts = accountService.findAllAccountsByType(pos);
        if (!accounts.isEmpty()) {
            accounts.forEach(System.out::println);
            System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
        } else System.out.println("No hay cuentas de ese tipo.");
    }

    /**
     *
     */
    public static void showAccountsByCurrency() {
        title("Buscar las cuentas por una moneda soportada");

        //
    }

    /**
     *
     */
    public static void showAccountTypeAndItsAccounts() {
        title("Listar un tipo de cuenta y cuentas relacionadas");

        //
    }

    /**
     *
     */
    public static void createNewAccount() {
        title("Crear una nueva cuenta");

        //
    }

    /**
     *
     */
    public static void increaseAccountBalanceById() {
        title("Incrementar el saldo de una cuenta por su id");

        Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
        Optional<Account> optAccount = accountService.findAccountById(id);
        if (optAccount.isPresent()) {
            Account account = optAccount.get();
            System.out.println("Saldo actual: " + account.getAmount());

            double amount = getLongIntPos("Introduzca la cantidad a ingresar: ").doubleValue();
            if (amount > 0)
                System.out.println(accountService.incrementAccountAmount(id, amount)
                        ? "Nuevo saldo: " + account.getAmount()
                        : "No ha sido posible ingresar la cantidad." );
            else System.out.println("Nada que ingresar.");

        } else System.out.println("No se ha encontrado la cuenta.");
    }

    /**
     *
     */
    public static void decrementAccountBalanceById() {
        title("Retirar del saldo de una cuenta por su id");

        Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
        Optional<Account> optAccount = accountService.findAccountById(id);
        if (optAccount.isPresent()) {
            Account account = optAccount.get();
            System.out.println("Saldo actual: " + account.getAmount());

            double amount = getLongIntPos("Introduzca la cantidad a retirar: ").doubleValue();
            if (amount > 0)
                System.out.println(accountService.decrementAccountAmount(id, amount)
                        ? "Nuevo saldo: " + account.getAmount()
                        : "La cuenta no tiene saldo suficiente.");
            else System.out.println("Nada que retirar.");

        } else System.out.println("No se ha encontrado la cuenta.");
    }

    /**
     *
     */
    public static void updateAccountById() {
        title("Actualizar una cuenta por su id");

        //
    }

    /**
     *
     */
    public static void deleteAccountById() {
        title("Borrar una cuenta por su id");

        Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
        if (accountService.accountExist(id)) {

            if (getYesNo("¿Desea confirmar el borrado? (S/N): ")) {

                System.out.println(accountService.deleteAccount(id)
                        ? "La cuenta ha sido borrada correctamente."
                        : "No ha sido posible eliminar la cuenta.");

            } else System.out.println("Borrado cancelado.");

        } else System.out.println("No se ha encontrado la cuenta.");
    }

    /**
     *
     */
    public static void transferBalanceFromAccountToAnotherById() {
        title("Transferir saldo de una cuenta a otra por sus id");

        Long id1 = getLongIntPos("Introduzca el ID de la cuenta de origen: ");
        Optional<Account> optAccount1 = accountService.findAccountById(id1);
        if (optAccount1.isPresent()) {
            Account account1 = optAccount1.get();
            System.out.println("Saldo en origen: " + account1.getAmount());

            double amount = getLongIntPos("Introduzca la cantidad a transferir : ").doubleValue();
            if (amount > 0) {

                Long id2 = getLongIntPos("Introduzca el ID de la cuenta de destino: ");
                if (!id2.equals(id1)) {
                    Optional<Account> optAccount2 = accountService.findAccountById(id2);
                    if (optAccount2.isPresent()) {
                        Account account2 = optAccount2.get();
                        System.out.println("Saldo en destino: " + account2.getAmount());

                        if (accountService.transferAccountAmount(id1, id2, amount)) {
                            System.out.println("La transferencia se ha realizado correctamente.");
                            System.out.println("Saldo actual en origen: " + account1.getAmount());
                            System.out.println("Saldo actual en destino: " + account2.getAmount());
                        } else System.out.println("No ha sido posible realizar la transferencia.");

                    } else System.out.println("No se ha encontrado la cuenta de destino.");

                } else System.out.println("El usuario de origen y de destino son el mismo.");

            } else System.out.println("Nada que transferir.");

        } else System.out.println("No se ha encontrado la cuenta de origen.");
    }
}
