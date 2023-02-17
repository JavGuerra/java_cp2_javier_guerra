package java_cp2_javier_guerra.services.implementations;

import java_cp2_javier_guerra.entities.Employee;
import java_cp2_javier_guerra.repositories.EmployeeRepository;
import java_cp2_javier_guerra.services.IEmployeeService;

import java.util.*;

public class EmployeeServiceImpl implements IEmployeeService {

    private final Map<Long, Employee> employees = new EmployeeRepository(true).getEmployees();

    @Override
    public Optional<Employee> addEmployee(Employee employee) {
        return Optional.ofNullable(employees.put(employee.getId(), employee));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public boolean deleteEmployee(long id) {
        return employees.remove(id) != null;
    }
}
