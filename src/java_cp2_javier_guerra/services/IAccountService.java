package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.enums.BankAccountType;
import java_cp2_javier_guerra.entities.enums.CurrencyType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAccountService {

    /**
     * Comprueba si existe una cuenta bancaria dado su id.
     * @param id de la cuenta.
     * @return existe/true o no/false.
     */
    boolean accountExist(Long id);

    /**
     * Añade una cuenta bancaria a la lista de cuentas.
     * @param account cuenta.
     * @return opcionalmente, la misma cuenta bancaria.
     */
    Optional<Account> addAccount(Account account);

    /**
     * Obtiene todas las cuentas bancarias de la base de datos.
     * @return cuentas bancarias o una lista vacía si no hay.
     */
    List<Account> getAllAccounts();

    /**
     * Obtiene una cuenta bancaria por su id.
     * @param id identificador de la cuenta bancaria.
     * @return opcionalmente, la cuenta bancaria.
     */
    Optional<Account> getAccountById(Long id);

    /**
     * Obtiene una cuenta bancaria por el id del cliente.
     * @param id de la cuenta.
     * @return opcionalmente, la cuenta bancaria.
     */
    Optional<Account> getAccountByCustomerId(Long id);

    /**
     * Obtiene una lista de cuentas bancarias del mismo tipo (analógico | digital).
     * @param numType tipo de cuenta.
     * @return cuentas bancarias o una lista vacía si no hay.
     */
    List<Account> getAllAccountsByType(byte numType);

    /**
     * Obtiene una lista de cuentas bancarias que soportan una moneda determinada.
     * @param numType tipo de moneda.
     * @return cuentas bancarias o una lista vacía si no hay.
     */
    List<Account> getAllAccountsByCurrency(byte numType);

    /**
     * Comprueba si la cuenta bancaria soporta un tipo de moneda determinado.
     * @param id de la cuenta bancaria.
     * @param numType tipo de moneda.
     * @return true si soporta la moneda, false en caso contrario.
     */
    boolean thereIsCurrency(Long id, byte numType);

    /**
     * Incrementa el saldo de la cuenta bancaria.
     * @param id El identificador de la cuenta bancaria.
     * @param amount Cantidad de dinero a ingresar en la cuenta.
     * @return true si se ingresó correctamente el dinero o false si hubo un problema y no se pudo ingresar.
     */
    boolean incrementAccountAmount(Long id, Double amount);

    /**
     * Decrementa el saldo de la cuenta bancaria.
     * @param id El identificador de la cuenta bancaria.
     * @param amount Cantidad de dinero a retirar de la cuenta.
     * @return true si se retiró correctamente el dinero o false si hubo un problema y no se pudo retirar.
     */
    boolean decrementAccountAmount(Long id, Double amount);

    /**
     * Transfiere saldo entre dos cuentas bancarias si es posible.
     * @param id1 cuenta de origen.
     * @param id2 cuenta de destino.
     * @param amount cantidad a transferir.
     * @return true si se pudo realizar la operación, false en caso contrario.
     */
    boolean transferAccountAmount(Long id1, Long id2, Double amount);

    /**
     * Borra una cuenta bancaria.
     * @param id de la cuenta a borrar.
     * @return true si se pudo realizar la operación, false en caso contrario.
     */
    boolean deleteAccount(Long id);
}
