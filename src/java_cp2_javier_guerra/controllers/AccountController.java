package java_cp2_javier_guerra.controllers;

import java_cp2_javier_guerra.entities.*;
import java_cp2_javier_guerra.entities.enums.*;
import java_cp2_javier_guerra.services.*;
import java_cp2_javier_guerra.services.implementations.*;

import java.util.*;

import static java_cp2_javier_guerra.entities.enums.AccountType.*;
import static java_cp2_javier_guerra.entities.enums.CurrencyType.*;
import static java_cp2_javier_guerra.utils.ConsoleInput.*;

/**
 * Opciones del menú de cuentas bancarias de la aplicación
 */
public abstract class AccountController {

    private static final IAccountService accountService = new AccountServiceImpl();
    private static final ICustomerService customerService = new CustomerServiceImpl();
    private static final ILoanService loanService = new LoanServiceImpl();

    /**
     * Comprueba si hay cuentas bancarias. Si no las hay, muestra un mensaje.
     * @return true si hay cuentas bancarias, false en caso contrario.
     */
    private static boolean thereAreAccounts() {
        boolean thereAreAccounts = false;
        if (!accountService.getAllAccounts().isEmpty()) thereAreAccounts = true;
        else {
            System.out.println("No hay cuentas bancarias.");
            if (getYesNo("¿Desea crear una nueva cuenta bancaria? (S/N): ")) createNewAccount();
        }
        return thereAreAccounts;
    }

    /**
     * Lista todas las cuentas bancarias disponibles (activas o no).
     */
    public static void showAccountList() {
        title("Listar todas las cuentas");

        if (thereAreAccounts()) {
            List<Account> accounts = accountService.getAllAccounts();
            accounts.forEach(System.out::println);
            System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
        }
    }

    /**
     * Muestra una cuenta bancaria con un determinado id de cuenta.
     */
    public static void showAccountById() {
        title("Buscar una cuenta por su id");

        if (thereAreAccounts()) {
            Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
            Optional<Account> account = accountService.getAccountById(id);
            System.out.println(account.isPresent() ? account.get() : "No se ha encontrado la cuenta.");
        }
    }

    /**
     * Muestra una cuenta bancaria cuyo cliente asociado tenga un DNI/NIE determinado.
     */
    public static void showAccountByClientDniNie() {
        title("Buscar una cuenta por el DNI/NIE del cliente");

        if (thereAreAccounts()) {
            String dniNie = getWord("Introduzca el DNI/NIE del cliente: ");
            Optional<Customer> customer = customerService.getCustomerByDniNie(dniNie);
            if (customer.isPresent()) {
                Optional<Account> account = accountService.getAccountByCustomerId(customer.get().getId());
                System.out.println(account.isPresent() ? account.get() : "No se ha encontrado la cuenta.");
            } else System.out.println("No se ha encontrado el cliente.");
        }
    }

    /**
     * Muestra las cuentas bancarias de un tipo de cuenta determinado.
     */
    public static void showAccountsByType() {
        title("Buscar las cuentas por el tipo de cuenta");

        if (thereAreAccounts()) {
            String msg = "Elija tipo:" + getBankAccountTypeList() + ": ";
            byte pos = getLongIntPosByRange(msg, 1L, (long) AccountType.values().length).byteValue();
            if (pos > 0) {
                --pos;
                System.out.println("Cuentas del tipo: " + AccountType.values()[pos].getName());
                List<Account> accounts = accountService.getAllAccountsByType(pos);
                if (!accounts.isEmpty()) {
                    accounts.forEach(System.out::println);
                    System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
                } else System.out.println("No hay cuentas de ese tipo.");
            }
        }
    }

    /**
     * Muestra las cuentas bancarias que soporten un tipo de moneda determinada.
     */
    public static void showAccountsByCurrency() {
        title("Buscar las cuentas por una moneda soportada");

        if (thereAreAccounts()) {
            var currencies = CurrencyType.values();
            String msg = "Elija tipo:" + getCurrencyTypeList() + ": ";
            byte pos = getLongIntPosByRange(msg, 1L, (long) currencies.length).byteValue();
            if (pos > 0) {
                --pos;
                System.out.println("Cuentas que soportan la moneda: " + currencies[pos].getName());
                List<Account> accounts = accountService.getAllAccountsByCurrency(pos);
                if (!accounts.isEmpty()) {
                    accounts.forEach(System.out::println);
                    System.out.println("Total: " + accounts.size() + (accounts.size() > 1 ? " cuentas." : " cuenta."));
                } else System.out.println("No hay cuentas que soporten esa moneda.");
            }
        }
    }

    /**
     * Muestra todas las cuentas bancarias agrupadas por su tipo de cuenta.
     * Obtiene un Map cuyas claves son los tipos de cuenta y cuyos valores son listas de cuentas de esos tipos.
     */
    public static void showAccountTypeAndItsAccounts() {
        title("Listar un tipo de cuenta y cuentas relacionadas");

        if (thereAreAccounts()) {
            Map<AccountType, List<Account>> AccountTypesAndItsAccounts = accountService.getAccountTypesAndItsAccounts();
            for (AccountType type : AccountTypesAndItsAccounts.keySet()) {
                System.out.println(type.getName() + ":");
                for (Account account : AccountTypesAndItsAccounts.get(type)) System.out.println(account);
            }
        }
    }

    /**
     * Crea una nueva cuenta bancaria y la añade a la lista de cuentas.
     */
    public static void createNewAccount() {
        title("Crear una nueva cuenta");

        if (!customerService.getAllCustomers().isEmpty()) {
            boolean create = true;
            String msg;
            long idCustomer;
            byte posAccountType = 0;
            byte posCurrencyType = 0;
            double amount = 0;

            System.out.println("Lista de clientes:");
            customerService.getAllCustomers().forEach(System.out::println);
            idCustomer = getLongIntPos("introduzca el ID del cliente: ");
            if (idCustomer != 0) {

                System.out.println("Tipo de cuenta:");
                msg = "Elija tipo:" + getBankAccountTypeList() + ": ";
                posAccountType = getLongIntPosByRange(msg, 1L, (long) AccountType.values().length).byteValue();
                if (posAccountType != 0) {

                    System.out.println("Tipo de moneda inicial:");
                    msg = "Elija tipo:" + getCurrencyTypeList() + ": ";
                    posCurrencyType = getLongIntPosByRange(msg, 1L, (long) CurrencyType.values().length).byteValue();
                    if (posCurrencyType != 0) {

                        System.out.println("Saldo inicial:");
                        amount = getDoublePos("Introduzca la cantidad: ");

                    } else create = false;
                } else create = false;
            } else create = false;

            if (create) {
                Account account = new Account(
                        accountService.newAccountId(),
                        amount,
                        AccountType.values()[--posAccountType],
                        new HashSet<>(Set.of(CurrencyType.values()[--posCurrencyType])),
                        idCustomer,
                        1L);
                        // idCreationEmployee: Se entiende que existe al menos un empleado que
                        // gestiona la creación de cuentas y ha hecho login en el sistema.
                accountService.addAccount(account);
                System.out.println("Nueva cuenta bancaria creada:\n" + account);

            } else System.out.println("No se creará la cuenta.");

        } else System.out.println("No hay clientes para crear una cuenta.");
    }

    /**
     * Incrementa o decrementa el saldo de una cuenta bancaria con un determinado id de cuenta.
     * @param mode true ingresar, false retirar.
     */
    public static void incrementOrDecrementAccountBalanceById(Boolean mode) {
        title((mode ? "Incrementar el" : "Retirar") + " saldo de una cuenta por su id");

        if (thereAreAccounts()) {
            String strMode = mode ? "ingresar" : "retirar";
            Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
            Optional<Account> optAccount = accountService.getAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();

                System.out.println("Saldo actual: " + account.getAmount());
                double amount = getDoublePos("Introduzca la cantidad a " + strMode + ": ");
                if (amount > 0) {
                    System.out.println(accountService.incrementOrDecrementAccountAmount(id, amount, mode)
                            ? "Nuevo saldo: " + account.getAmount()
                            : "No ha sido posible " + strMode + " la cantidad.");
                } else System.out.println("Nada que " + strMode + ".");

            } else System.out.println("No se ha encontrado la cuenta.");
        }
    }

    /**
     * Actualiza los datos de una cuenta bancaria con un determinado id de cuenta.
     * Modifica su saldo, tipo de cuenta y lista de monedas soportadas.
     */
    public static void updateAccountById() {
        title("Actualizar una cuenta por su id");

        if (thereAreAccounts()) {
            Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
            Optional<Account> optAccount = accountService.getAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                System.out.println("Cuenta: " + account);

                System.out.println("Cambiar: (1) propietario, (2) saldo, (3) tipo, (4) moneda, (5) estado.");
                byte opt = getLongIntPosByRange("¿Qué opción desea? ",1L, 5L).byteValue();
                switch (opt) {

                    case 1 -> {
                        System.out.println("Cambiar el propietario de la cuenta.");

                        if (customerService.getAllCustomers().size() > 1) {
                            Optional<Customer> optCustomer = customerService.getCustomerById(account.getIdCustomer());
                            if (optCustomer.isPresent()) {
                                Customer customer = optCustomer.get();

                                System.out.println("Propietario actual ID: " + customer.getId() + ", nombre: " + customer.getName());
                                System.out.println("Listado de clientes: ");
                                for (Customer icustomer : customerService.getAllCustomers())
                                    if (!customer.getId().equals(icustomer.getId())) System.out.println(icustomer);

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
                                    System.out.println("No se ha encontrado el nuevo cliente.");
                                }

                            } else System.out.println("No se ha encontrado el cliente.");
                        } else System.out.println("No hay otros clientes de cuentas bancarias.");
                    }

                    case 2 -> {
                        System.out.println("Cambiar el saldo de la cuenta.");

                        System.out.println("Saldo actual: " + account.getAmount());
                        double amount = getDoublePos("Introduzca la cantidad: ");
                        account.setAmount(amount);
                        System.out.println("Nuevo saldo: " + account.getAmount());
                    }

                    case 3 -> {
                        System.out.println("Cambiar el tipo de cuenta.");

                        System.out.println("Tipo de cuenta actual: (" + (account.getType().ordinal() + 1) + ") " + account.getType().getName());
                        String msg = "Elija nuevo tipo:" + getBankAccountTypeListExceptOne(account.getType()) + ": ";
                        byte pos = getLongIntPosByRange(msg, 1L, (long) AccountType.values().length).byteValue();

                        if (pos - 1 != account.getType().ordinal()) {
                            account.setType(AccountType.values()[pos - 1]);
                            System.out.println("Nuevo tipo de cuenta: " + account.getType().getName());
                        } else System.out.println("Nada que cambiar.");
                    }

                    case 4 -> {
                        System.out.println("Cambiar un tipo de moneda.");

                        byte mode = 2;
                        var currencies = CurrencyType.values();

                        if (account.getCurrencies().size() > 0) {
                            System.out.println("Monedas soportadas: " + account.getCurrencies());
                            mode = getLongIntPosByRange("¿Desea (1) añadir o (2) quitar una moneda?: ", 1L, 2L).byteValue();
                        } else System.out.println("La cuenta no tiene monedas soportadas. Añada una.");

                        System.out.println((mode == 1 ? "Añadir" : "Quitar") + " un tipo de moneda.");
                        String msg = "Elija tipo:" + getCurrencyTypeList() + ": ";
                        byte pos = getLongIntPosByRange(msg, 1L, (long) currencies.length).byteValue();

                        if (pos > 0) {
                            --pos;
                            boolean currencyExist = accountService.currencyExistInAccount(account.getId(), pos);
                            if (mode == 1) {
                                if (!currencyExist) {
                                    account.setCurrency(currencies[pos]);
                                    System.out.println("Se ha añadido la moneda: " + currencies[pos].getName());
                                } else System.out.println("La moneda ya está soportada.");
                            } else {
                                if (currencyExist) {
                                    account.removeCurrency(currencies[pos]);
                                    System.out.println("Se ha quitado la moneda: " + currencies[pos].getName());
                                } else System.out.println("La moneda no está soportada.");
                            }
                        }
                    }

                    case 5 -> {
                        System.out.println("Cambiar el estado (" + (account.isActive() ? "activo" : "inactivo") + ") de la cuenta.");

                        if (getYesNo("¿Desea confirmar el cambio de estado? (S/N): ")) {
                            account.setActive(!account.isActive());
                            System.out.println("La cuenta ahora está: " + (account.isActive() ? "activa" : "inactiva"));
                        } else System.out.println("Cambio de estado cancelado.");
                    }
                }
            } else System.out.println("No se ha encontrado la cuenta.");
        }
    }

    /**
     * Borra una cuenta bancaria con un determinado id de cuenta.
     */
    public static void deleteAccountById() {
        title("Borrar una cuenta por su id");

        if (thereAreAccounts()) {
            Long id = getLongIntPos("Introduzca el ID de la cuenta: ");
            if (accountService.accountExistById(id)) {
                if (!loanService.loanExistById(id)) {

                    if (getYesNo("¿Desea confirmar el borrado? (S/N): ")) {
                        System.out.println(accountService.deleteAccount(id)
                                ? "La cuenta ha sido borrada correctamente."
                                : "No ha sido posible eliminar la cuenta.");
                    } else System.out.println("Borrado cancelado.");

                } else System.out.println("No es posible borrar la cuenta.\nHay un préstamo asociado.");
            } else System.out.println("No se ha encontrado la cuenta.");
        }
    }

    /**
     * Transfiere el saldo indicado de una cuenta bancaria a otra indicando sus respectivos id de cuenta, si es posible.
     */
    public static void transferBalanceFromAccountToAnotherById() {
        title("Transferir saldo entre dos cuentas por sus id");

        if (thereAreAccounts()) {
            Long id1 = getLongIntPos("Introduzca el ID de la cuenta de origen: ");
            Optional<Account> optAccount1 = accountService.getAccountById(id1);
            if (optAccount1.isPresent()) {
                Account account1 = optAccount1.get();
                System.out.println("Saldo en origen: " + account1.getAmount());

                double amount = getDoublePos("Introduzca la cantidad a transferir : ");
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
                        } else System.out.println("El cliente de origen y de destino son el mismo.");
                    } else System.out.println("Saldo insuficiente para transferir.");
                } else System.out.println("Nada que transferir.");
            } else System.out.println("No se ha encontrado la cuenta de origen.");
        }
    }
}