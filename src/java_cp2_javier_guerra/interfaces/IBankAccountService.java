package java_cp2_javier_guerra.interfaces;

import java_cp2_javier_guerra.entities.BankAccount;
import java_cp2_javier_guerra.enums.BankAccountType;

import java.util.List;
import java.util.Optional;

/**
 * Tipo ABSTRACTO
 * Interfaz con operaciones para trabajar con cuentas bancarias (BankAccount)
 */

public interface IBankAccountService {

    boolean accountExist(Long id);

    void addAccount(BankAccount account);

    /**
     * Recupera todas las cuentas bancarias de base de datos
     * @return cuentas bancarias o una lista vacía si no hay
     */
    List<BankAccount> findAll();

    /**
     * Recupera una cuenta bancaria por su id
     *
     * @param id identificador de la cuenta bancaria
     * @return la cuenta bancaria
     */
    Optional<BankAccount> findById(Long id);

    Optional<BankAccount> findByNif(String nif);

    List<BankAccount> findAllByType(byte numType);

    /**
     * Crea un nuevo objeto cuenta bancaria y lo almacena en base de datos
     * @param nif El dni del cliente titular
     * @param type El tipo de cuenta bancaria
     * @param amount El saldo inicial
     * @return La nueva cuenta bancaria 
     */
    BankAccount create(String nif, BankAccountType type, Double amount);

    /**
     * Actualiza los datos de una cuenta bancaria
     * @param bankAccount la cuenta bancaria a actualizar
     * @return la cuenta bancaria actualizada
     */
    BankAccount update(BankAccount bankAccount);

    /**
     * Incrementa el saldo de la cuenta bancaria
     * @param id El identificador de la cuenta bancaria
     * @param amount Cantidad de dinero a ingresar en la cuenta
     * @return true si se ingresó correctamente el dinero o false si hubo un problema y no se pudo ingresar
     */
    boolean incrementAmount(Long id, Double amount);

    /**
     * Decrementa el saldo de la cuenta bancaria
     * @param id El identificador de la cuenta bancaria
     * @param amount Cantidad de dinero a retirar de la cuenta
     * @return true si se retiró correctamente el dinero o false si hubo un problema y no se pudo retirar
     */
    boolean decrementAmount(Long id, Double amount);

    boolean transferAmount(Long id1, Long id2, Double amount);

    boolean deleteAccount(Long id);
}
