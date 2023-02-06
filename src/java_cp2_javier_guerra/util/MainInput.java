package java_cp2_javier_guerra.util;

import java_cp2_javier_guerra.entities.BankAccount;
import java_cp2_javier_guerra.interfaces.IBankAccountService;

import java.util.Optional;

import static java_cp2_javier_guerra.util.ConsoleInput.getLongIntPos;

public abstract class MainInput {

    /**
     * Comprueba que exista una cuenta bancaria por su Id
     * @param message Mensaje para obtener datos
     * @param accountService Servicio de cuentas utilizado
     * @return Id de la cuenta bancaria
     */
    public static Long checkId(String message, IBankAccountService accountService) {
        Long id = getLongIntPos(message);
        if (id > 0) {
            Optional<BankAccount> optAccount = Optional.ofNullable(accountService.findById(id));
            if (optAccount.isPresent()) return id;
        }
        return 0L;
    }

    /**
     * Busca una cuenta bancaria por su Id
     * @param message Mensaje para obtener datos
     * @param accountService Servicio de cuentas utilizado
     * @return Cuenta bancaria
     */
    public static BankAccount getAccountById(String message, IBankAccountService accountService) {
        Long id = getLongIntPos(message);
        if (id > 0) {
            Optional<BankAccount> optAccount = Optional.ofNullable(accountService.findById(id));
            if (optAccount.isPresent()) return optAccount.get();
        }
        return null;
    }
}
