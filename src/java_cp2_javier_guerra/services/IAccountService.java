package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.BankAccountType;

import java.util.List;
import java.util.Optional;

/**
 * Tipo ABSTRACTO
 * Interfaz con operaciones para trabajar con cuentas bancarias (BankAccount)
 */

public interface IAccountService {

    boolean accountExist(Long id);

    void addAccount(Account account);

    /**
     * Recupera todas las cuentas bancarias de base de datos
     * @return cuentas bancarias o una lista vacía si no hay
     */
    List<Account> getAllAccounts();

    /**
     * Recupera una cuenta bancaria por su id
     *
     * @param id identificador de la cuenta bancaria
     * @return la cuenta bancaria
     */
    Optional<Account> findAccountById(Long id);

    Optional<Account> findAccountByCustomerId(Long id);

    List<Account> findAllAccountsByType(byte numType);

    /**
     * Crea un nuevo objeto cuenta bancaria y lo almacena en base de datos
     * @param nif El dni del cliente titular
     * @param type El tipo de cuenta bancaria
     * @param amount El saldo inicial
     * @return La nueva cuenta bancaria 
     */
    Account createAccount(String nif, BankAccountType type, Double amount);

    /**
     * Actualiza los datos de una cuenta bancaria
     * @param account la cuenta bancaria a actualizar
     * @return la cuenta bancaria actualizada
     */
    Account updateAccount(Account account);

    /**
     * Incrementa el saldo de la cuenta bancaria
     * @param id El identificador de la cuenta bancaria
     * @param amount Cantidad de dinero a ingresar en la cuenta
     * @return true si se ingresó correctamente el dinero o false si hubo un problema y no se pudo ingresar
     */
    boolean incrementAccountAmount(Long id, Double amount);

    /**
     * Decrementa el saldo de la cuenta bancaria
     * @param id El identificador de la cuenta bancaria
     * @param amount Cantidad de dinero a retirar de la cuenta
     * @return true si se retiró correctamente el dinero o false si hubo un problema y no se pudo retirar
     */
    boolean decrementAccountAmount(Long id, Double amount);

    boolean transferAccountAmount(Long id1, Long id2, Double amount);

    boolean deleteAccount(Long id);
}