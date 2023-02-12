package java_cp2_javier_guerra.controllers;

import java_cp2_javier_guerra.entities.*;
import java_cp2_javier_guerra.entities.enums.*;
import java_cp2_javier_guerra.services.*;
import java_cp2_javier_guerra.services.implementations.*;

import java.util.List;
import java.util.Optional;

import static java_cp2_javier_guerra.entities.enums.BankAccountType.*;
import static java_cp2_javier_guerra.entities.enums.CurrencyType.*;
import static java_cp2_javier_guerra.utils.ConsoleInput.*;

/**
 * Opciones del menú de cuentas bancarias de la aplicación
 */
public abstract class AccountController {

    private static final IAccountService accountService = new AccountServiceImpl();
    private static final ICustomerService customerService = new CustomerServiceImpl();

    private static final Boolean thereAreAccounts = accountService.getAllAccounts().size() > 0;

    /**
     * Lista todas las cuentas bancarias disponibles (activas o no).
     */
    public static void showAccountList() {
        title("Listar todas las cuentas");

        if (thereAreAccounts) {
            List<Account> accounts = accountService.getAllAccounts();
            accounts.forEach(System.out::println);
            System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Muestra una cuenta bancaria con un determinado id de cuenta.
     */
    public static void showAccountById() {
        title("Buscar una cuenta por su id");

        if (thereAreAccounts) {
            Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
            Optional<Account> account = accountService.getAccountById(id);
            System.out.println(account.isPresent() ? account.get() : "No se ha encontrado la cuenta.");
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Muestra una cuenta bancaria cuyo cliente asociado tenga un NIF determinado.
     */
    public static void showAccountByUserNIF() {
        title("Buscar una cuenta por el NIF del usuario");

        if (thereAreAccounts) {
            String nif = getWord("Introduzca el NIF del usuario: ");
            Optional<Customer> customer = customerService.getCustomerByNif(nif);
            if (customer.isPresent()) {
                Optional<Account> account = accountService.getAccountByCustomerId(customer.get().getId());
                System.out.println(account.isPresent() ? account.get() : "No se ha encontrado la cuenta.");
            } else System.out.println("No se ha encontrado el usuario.");
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Muestra las cuentas bancarias de un tipo de cuenta determinado.
     */
    public static void showAccountsByType() {
        title("Buscar las cuentas por el tipo de cuenta");

        if (thereAreAccounts) {
            String msg = "Elija tipo:" + getBankAccountTypeList() + ": ";
            byte pos = getLongIntPosByRange(msg, 1L, (long) BankAccountType.values().length).byteValue();
            if (pos > 0) {
                System.out.println("Cuentas del tipo: " + BankAccountType.values()[pos -1].getName());
                List<Account> accounts = accountService.getAllAccountsByType(pos);
                if (!accounts.isEmpty()) {
                    accounts.forEach(System.out::println);
                    System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
                } else System.out.println("No hay cuentas de ese tipo.");
            }
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Muestra las cuentas bancarias que soporten un tipo de moneda determinada.
     */
    public static void showAccountsByCurrency() {
        title("Buscar las cuentas por una moneda soportada");

        if (thereAreAccounts) {
            String msg = "Elija tipo:" + getCurrencyTypeList() + ": ";
            byte pos = getLongIntPosByRange(msg, 1L, (long) CurrencyType.values().length).byteValue();
            if (pos > 0) {
                System.out.println("Cuentas que soportan la moneda: " + CurrencyType.values()[pos -1].getName());
                List<Account> accounts = accountService.getAllAccountsByCurrency(pos);
                if (!accounts.isEmpty()) {
                    accounts.forEach(System.out::println);
                    System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
                } else System.out.println("No hay cuentas que soporten esa moneda.");
            }
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Muestra todas las cuentas bancarias agrupadas por su tipo de cuenta.
     * Obtiene un Map cuyas claves son los tipos de cuenta y cuyos valores son listas de cuentas de esos tipos.
     */
    public static void showAccountTypeAndItsAccounts() {
        title("Listar un tipo de cuenta y cuentas relacionadas");

        if (thereAreAccounts) {
            //
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Crea una nueva cuenta bancaria y la añade a la lista de cuentas.
     */
    public static void createNewAccount() {
        title("Crear una nueva cuenta");

        if (thereAreAccounts) {
           //
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Incrementa el saldo de una cuenta bancaria con un determinado id de cuenta.
     */
    public static void increaseAccountBalanceById() {
        title("Incrementar el saldo de una cuenta por su id");

        if (thereAreAccounts) {
            Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
            Optional<Account> optAccount = accountService.getAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                System.out.println("Saldo actual: " + account.getAmount());

                double amount = getLongIntPos("Introduzca la cantidad a ingresar: ").doubleValue();
                if (amount > 0)
                    System.out.println(accountService.incrementAccountAmount(id, amount)
                            ? "Nuevo saldo: " + account.getAmount()
                            : "No ha sido posible ingresar la cantidad.");
                else System.out.println("Nada que ingresar.");

            } else System.out.println("No se ha encontrado la cuenta.");
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Decrementa el saldo de una cuenta bancaria con un determinado id de cuenta, si es posible.
     */
    public static void decrementAccountBalanceById() {
        title("Retirar saldo de una cuenta por su id");

        if (thereAreAccounts) {
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
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Actualiza los datos de una cuenta bancaria con un determinado id de cuenta.
     * Modifica su saldo, tipo de cuenta y lista de monedas soportadas.
     */
    public static void updateAccountById() {
        title("Actualizar una cuenta por su id");

        if (thereAreAccounts) {
            Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
            Optional<Account> optAccount = accountService.getAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();

                System.out.println("Cambiar: (1) propietario (2) saldo (3) tipo (4) moneda (5) estado.");
                byte opt = getLongIntPosByRange("¿Qué cambio desea realizar? ",1L, 5L).byteValue();

                switch (opt) {

                    case 1 -> {
                        System.out.println("Cambiar el propietario de la cuenta.");
                        if (customerService.getAllCustomers().size() > 1) {
                            Optional<Customer> optCustomer = customerService.getCustomerById(account.getIdCustomer());
                            if (optCustomer.isPresent()) {
                                Customer customer = optCustomer.get();

                                System.out.println("Propietario actual ID: " + customer.getId() + ", nombre: " + customer.getName());
                                System.out.println("Listado de clientes: ");
                                for (Customer icustomer : customerService.getAllCustomers()) {
                                    if (!customer.getId().equals(icustomer.getId()))
                                        System.out.println("ID: " + icustomer.getId() + ". nombre: " + icustomer.getName());
                                }

                                Long newId;
                                Optional<Customer> optNewCustomer;
                                while (true) {
                                    newId = getLongIntPos("Introduzca el ID del nuevo cliente: ");
                                    if (newId == 0 || newId.equals(customer.getId())) {
                                        System.out.println("Nada que cambiar.");
                                        break;
                                    }
                                    optNewCustomer = customerService.getCustomerById(newId);
                                    if (optNewCustomer.isPresent()) {

                                        Customer newCustomer = optNewCustomer.get();
                                        account.setIdCustomer(newId);
                                        System.out.println("Nuevo propietario ID: " + newId + ", nombre: " + newCustomer.getName());
                                        break;

                                    }
                                    System.out.println("No se ha encontrado el cliente.");
                                }

                            } else System.out.println("No se ha encontrado el cliente.");
                        } else System.out.println("No hay otros usuarios de cuentas bancarias.");
                    }

                    case 2 -> {
                        System.out.println("Cambiar el saldo de la cuenta.");
                        System.out.println("Saldo actual: " + account.getAmount());
                        double amount = getLongIntPos("Introduzca la cantidad: ").doubleValue();
                        if (amount > 0) {
                            account.setAmount(amount);
                            System.out.println("Nuevo saldo: " + account.getAmount());
                        } else System.out.println("Nada que cambiar.");
                    }

                    case 3 -> {
                        System.out.println("Cambiar el tipo de cuenta.");
                        System.out.println("Tipo de cuenta actual: (" + (account.getType().ordinal() + 1) + ") " + account.getType().getName());
                        String msg = "Elija nuevo tipo:" + getBankAccountTypeListExceptOne(account.getType()) + ": ";
                        byte pos = getLongIntPosByRange(msg, 1L, (long) BankAccountType.values().length).byteValue();
                        if (pos - 1 != account.getType().ordinal()) {
                            account.setType(BankAccountType.values()[pos - 1]);
                            System.out.println("Nuevo tipo de cuenta: " + account.getType().getName());
                        } else System.out.println("Nada que cambiar.");
                    }

                    case 4 -> {
                        System.out.println("Cambiar un tipo de moneda.");
                        byte mode;
                        if (account.getCurrencies().size() > 0) {
                            System.out.println("Monedas soportadas: " + account.getCurrencies());
                            mode = getLongIntPosByRange("¿Desea (1) añadir o (2) quitar una moneda?: ", 1L, 2L).byteValue();
                        } else {
                            System.out.println("La cuenta no tiene monedas soportadas. Añada una.");
                            mode = 2;
                        }
                        String msg = "Elija tipo:" + getCurrencyTypeList() + ": ";
                        byte pos = getLongIntPosByRange(msg, 1L, (long) CurrencyType.values().length).byteValue();
                        if (pos > 0) {
                            --pos;
                            if (mode == 1) {
                                if (!accountService.thereIsCurrency(account.getId(), pos)) {
                                    account.setCurrency(CurrencyType.values()[pos]);
                                    System.out.println("Añadida la moneda: " + CurrencyType.values()[pos].getName());
                                } else System.out.println("La moneda ya está soportada.");
                            } else {
                                if (accountService.thereIsCurrency(account.getId(), pos)) {
                                    // TODO Quitar moneda
                                } else System.out.println("La moneda no está soportada.");
                            }
                        }
                    }

                    case 5 -> {
                        System.out.println("Cambiar el estado (" + (account.isActive() ? "activo" : "inactivo") + ") de la cuenta.");
                        account.setActive(!account.isActive());
                        System.out.println("La cuenta está ahora: " + (account.isActive() ? "activa" : "inactiva"));
                    }
                }

            } else System.out.println("No se ha encontrado la cuenta.");
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Borra una cuenta bancaria con un determinado id de cuenta.
     */
    public static void deleteAccountById() {
        title("Borrar una cuenta por su id");

        // TODO Si la cuenta tiene un préstamo, no borrar

        if (thereAreAccounts) {
            Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
            if (accountService.accountExist(id)) {

                if (getYesNo("¿Desea confirmar el borrado? (S/N): ")) {
                    System.out.println(accountService.deleteAccount(id)
                            ? "La cuenta ha sido borrada correctamente."
                            : "No ha sido posible eliminar la cuenta.");
                } else System.out.println("Borrado cancelado.");

            } else System.out.println("No se ha encontrado la cuenta.");
        } else System.out.println("No hay cuentas bancarias.");
    }

    /**
     * Transfiere el saldo indicado de una cuenta bancaria a otra indicando sus respectivos id de cuenta, si es posible.
     */
    public static void transferBalanceFromAccountToAnotherById() {
        title("Transferir saldo entre dos cuentas por sus id");

        if (thereAreAccounts) {
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
        } else System.out.println("No hay cuentas bancarias.");
    }
}