package java_cp2_javier_guerra.controllers;

import java_cp2_javier_guerra.entities.*;
import java_cp2_javier_guerra.services.*;

import java.util.List;
import java.util.Optional;

import static java_cp2_javier_guerra.entities.BankAccountType.getBankAccountTypeList;
import static java_cp2_javier_guerra.utils.ConsoleInput.*;

/**
 * Opciones del menú de cuentas bancarias de la aplicación
 */
public abstract class AccountController {

    private static final IAccountService accountService = new AccountServiceImpl();
    private static final ICustomerService customerService = new CustomerServiceImpl();

    /**
     * Lista todas las cuentas bancarias disponibles (activas o no).
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
     * Muestra una cuenta bancaria con un determinado id de cuenta.
     */
    public static void showAccountById() {
        title("Buscar una cuenta por su id");

        Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
        Optional<Account> account = accountService.getAccountById(id);
        System.out.println(account.isPresent() ? account.get() : "No se ha encontrado la cuenta.");
    }

    /**
     * Muestra una cuenta bancaria cuyo cliente asociado tenga un NIF determinado.
     */
    public static void showAccountByUserNIF() {
        title("Buscar una cuenta por el NIF del usuario");

        String nif = getWord("Introduzca el NIF del usuario: ");
        Optional<Customer> customer = customerService.getCustomerByNif(nif);
        if (customer.isPresent()) {
            Optional<Account> account = accountService.getAccountByCustomerId(customer.get().getId());
            System.out.println(account.isPresent() ? account.get() : "No se ha encontrado la cuenta.");
        } else System.out.println("No se ha encontrado el usuario.");
    }

    /**
     * Muestra las cuentas bancarias de un tipo de cuenta determinado.
     */
    public static void showAccountsByType() {
        title("Buscar las cuentas por el tipo de cuenta");

        String msg = "Elija tipo:" + getBankAccountTypeList() + ": ";
        byte pos = getLongIntPosByRange(msg, 1L, (long) BankAccountType.values().length).byteValue();
        List<Account> accounts = accountService.getAllAccountsByType(pos);
        if (!accounts.isEmpty()) {
            accounts.forEach(System.out::println);
            System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
        } else System.out.println("No hay cuentas de ese tipo.");
    }

    /**
     * Muestra las cuentas bancarias que soporten un tipo de moneda determinada.
     */
    public static void showAccountsByCurrency() {
        title("Buscar las cuentas por una moneda soportada");

        //
    }

    /**
     * Muestra todas las cuentas bancarias agrupadas por su tipo de cuenta.
     * Obtiene un Map cuyas claves son los tipos de cuenta y cuyos valores son listas de cuentas de esos tipos.
     */
    public static void showAccountTypeAndItsAccounts() {
        title("Listar un tipo de cuenta y cuentas relacionadas");

        //
    }

    /**
     * Crea una nueva cuenta bancaria y la añade a la lista de cuentas.
     */
    public static void createNewAccount() {
        title("Crear una nueva cuenta");

        //
    }

    /**
     * Incrementa el saldo de una cuenta bancaria con un determinado id de cuenta.
     */
    public static void increaseAccountBalanceById() {
        title("Incrementar el saldo de una cuenta por su id");

        Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
        Optional<Account> optAccount = accountService.getAccountById(id);
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
     * Decrementa el saldo de una cuenta bancaria con un determinado id de cuenta, si es posible.
     */
    public static void decrementAccountBalanceById() {
        title("Retirar saldo de una cuenta por su id");

        Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
        Optional<Account> optAccount = accountService.getAccountById(id);
        if (optAccount.isPresent()) {
            Account account = optAccount.get();
            System.out.println("Saldo actual: " + account.getAmount());

            double amount = getLongIntPos("Introduzca la cantidad a retirar: ").doubleValue();
            if (amount > 0)
                if (amount <= account.getAmount())
                    System.out.println(accountService.decrementAccountAmount(id, amount)
                            ? "Nuevo saldo: " + account.getAmount()
                            : "No ha sido posible retirar la cantidad.");
                else System.out.println("Saldo insuficiente para retirar.");
            else System.out.println("Nada que retirar.");

        } else System.out.println("No se ha encontrado la cuenta.");
    }

    /**
     * Actualiza los datos de una cuenta bancaria con un determinado id de cuenta.
     * Modifica su saldo, tipo de cuenta y lista de monedas soportadas.
     */
    public static void updateAccountById() {
        title("Actualizar una cuenta por su id");

        Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
        Optional<Account> optAccount = accountService.getAccountById(id);
        if (optAccount.isPresent()) {
            Account account = optAccount.get();

            // amount
            // type
            // currencies

        } else System.out.println("No se ha encontrado la cuenta.");
    }

    /**
     * Borra una cuenta bancaria con un determinado id de cuenta.
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
     * Transfiere el saldo indicado de una cuenta bancaria a otra indicando sus respectivos id de cuenta, si es posible.
     */
    public static void transferBalanceFromAccountToAnotherById() {
        title("Transferir saldo entre dos cuentas por sus id");

        Long id1 = getLongIntPos("Introduzca el ID de la cuenta de origen: ");
        Optional<Account> optAccount1 = accountService.getAccountById(id1);
        if (optAccount1.isPresent()) {
            Account account1 = optAccount1.get();
            System.out.println("Saldo en origen: " + account1.getAmount());

            double amount = getLongIntPos("Introduzca la cantidad a transferir : ").doubleValue();
            if (amount > 0) {
                if (amount <= account1.getAmount()) {
                    Long id2 = getLongIntPos("Introduzca el ID de la cuenta de destino: ");
                    if (!id2.equals(id1)) {

                        Optional<Account> optAccount2 = accountService.getAccountById(id2);
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
                } else System.out.println("Saldo insuficiente para transferir.");
            } else System.out.println("Nada que transferir.");
        } else System.out.println("No se ha encontrado la cuenta de origen.");
    }
}
