package java_cp2_javier_guerra;

import static java_cp2_javier_guerra.MenuOptions.*;
import static java_cp2_javier_guerra.utils.ConsoleInput.*;

public class Main {

    public static void main(String[] args) {
        byte opt;
        String menu = """
            ┌─────┬─────────────────────────────┐
            │ Opc.│  Menú de cuentas bancarias  │
            ├─────┴─────────────────────────────┘
            │  1.  Listar todas las cuentas
            │  2.  Buscar una cuenta por su id
            │  3.  Buscar una cuenta por el NIF del usuario1
            │  4.  Buscar las cuentas por el tipo de cuenta
            │  5.  Buscar las cuentas por una moneda soportada
            │  6.  Listar un tipo de cuenta y cuentas relacionadas
            │  7.  Crear una nueva cuenta
            │  8.  Incrementar el saldo de una cuenta por su id
            │  9.  Retirar del saldo de una cuenta por su id
            │ 10.  Actualizar una cuenta por su id
            │ 11.  Borrar una cuenta por su id
            │ 12.  Transferir saldo de una cuenta a otra por sus id
            │  0.  Salir de la aplicación
            └───────────────────────────────┐""";

        clearConsole();
        System.out.println(menu);

        while (true) {
            opt = getLongIntPosByRange("  Seleccione una opción (0-12): ", 0L, 12L).byteValue();

            if (opt == 0) break;

            switch (opt) {
                case  1 -> showAccountList();
                case  2 -> showAccountById();
                case  3 -> showAccountByUserNIF();
                case  4 -> showAccountsByType();
                case  5 -> showAccountsByCurrency();
                case  6 -> showAccountTypeAndItsAccounts();
                case  7 -> createNewAccount();
                case  8 -> increaseAccountBalanceById();
                case  9 -> decrementAccountBalanceById();
                case 10 -> updateAccountById();
                case 11 -> deleteAccountById();
                case 12 -> transferBalanceFromAccountToAnotherById();
            }

            System.out.println("\nPulse <Intro> para continuar.");
            getEnter();

            clearConsole();
            System.out.println(menu);
        }

        closeScanner();
        System.out.println("La aplicación ha finalizado.");
    }
}