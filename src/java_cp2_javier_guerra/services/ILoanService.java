package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Loan;

import java.util.List;
import java.util.Optional;

public interface ILoanService {

    /**
     * Añade un préstamo a la lista de préstamos.
     * @param loan préstamo a añadir.
     * @return opcionalmente, el mismo préstamo.
     */
    Optional<Loan> addLoan(Loan loan);

    /**
     * Obtiene todos los préstamos.
     * @return lista de préstamos.
     */
    List<Loan> getAllLoans();

    /**
     * Borra un préstamo de la lista.
     * @param id del préstamo.
     * @return true si se borró el préstamo o false en caso contrario.
     */
    boolean deleteLoan(Long id);
}
