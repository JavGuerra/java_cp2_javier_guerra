package java_cp2_javier_guerra.services.implementations;

import java_cp2_javier_guerra.entities.Customer;
import java_cp2_javier_guerra.entities.Loan;
import java_cp2_javier_guerra.repositories.LoanRepository;
import java_cp2_javier_guerra.services.ILoanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LoanServiceImpl implements ILoanService {

    private final Map<Long, Loan> loans = new LoanRepository(true).getLoans();

    @Override
    public Optional<Loan> addLoan(Loan loan) {
        return Optional.ofNullable(loans.put(loan.getId(), loan));
    }

    @Override
    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans.values());
    }

    @Override
    public boolean loanExistById(Long accountId) {
        boolean thereIsLoan = false;
        if (accountId != null && accountId > 0) {
            for (Loan loan : loans.values()) {
                if (loan.getAccountId().equals(accountId)) {
                    thereIsLoan = true;
                    break;
                }
            }
        }
        return thereIsLoan;
    }

    @Override
    public boolean deleteLoan(Long id) {
        return loans.remove(id) != null;
    }
}
