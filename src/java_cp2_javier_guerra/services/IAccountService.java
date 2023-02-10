package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.BankAccountType;
import java_cp2_javier_guerra.entities.CurrencyType;

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
     * Crea un nuevo objeto cuenta bancaria y lo almacena en base de datos.
     * @param id de la cuenta.
     * @param amount saldo inicial de la cuenta.
     * @param type tipo de cuenta
     * @param currencies monedas
     * @param idCustomer id del cliente
     * @param idCreationEmployee id del empleado que abrió la cuenta.
     * @return opcionalmente, la nueva cuenta bancaria.
     */
    Optional<Account> createAccount(Long id, Double amount, BankAccountType type, Set<CurrencyType> currencies, Long idCustomer, Long idCreationEmployee);

    /**
     * Actualiza los datos de una cuenta bancaria.
     * @param account la cuenta bancaria a actualizar.
     * @return opcionalmente, la cuenta bancaria actualizada.
     */
    Optional<Account> updateAccount(Account account);

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
     * Tranfiere saldo entre dos cuentas bancarias si es posible.
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
