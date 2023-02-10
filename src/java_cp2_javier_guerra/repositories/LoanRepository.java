package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Loan;

import java.util.HashMap;
import java.util.Map;

public class LoanRepository {

    private Map<Long, Loan> loans = new HashMap<>();

    public LoanRepository() {
        setLoans(exampleLoans());
    }

    public Map<Long, Loan> getLoans() {
        return loans;
    }

    public void setLoans(Map<Long, Loan> loans) {
        this.loans = loans;
    }

    private Map<Long, Loan> exampleLoans() {
        var loan1 = new Loan(1L,1L, 1L, 5000d, 5);

        return new HashMap<>(Map.of(1L, loan1));
    }
}
