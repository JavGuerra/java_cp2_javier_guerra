package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Loan;

import java.util.HashMap;
import java.util.Map;

public class LoanRepository {

    private Map<Long, Loan> loans = new HashMap<>();

    public LoanRepository(Boolean data) {
        setLoans(exampleLoans(data));
    }

    public Map<Long, Loan> getLoans() {
        return loans;
    }

    public void setLoans(Map<Long, Loan> loans) {
        this.loans = loans;
    }

    /**
     * Genera una lista de préstamos de ejemplo o una lista vacía.
     * @param data true incluye datos de ejemplo, false no lo hace.
     * @return Lista de préstamos.
     */
    private Map<Long, Loan> exampleLoans(Boolean data) {
        if (data == null) data = false;

        Map<Long, Loan> loans = new HashMap<>();

        if (data) {
            var loan1 = new Loan(1L, 1L, 1L, 5000d, 5);

            loans.putAll(Map.of(1L, loan1));
        }

        return loans;
    }
}
