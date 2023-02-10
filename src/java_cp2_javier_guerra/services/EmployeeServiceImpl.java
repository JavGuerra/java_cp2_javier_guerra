package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Employee;

import java.util.*;

import static java_cp2_javier_guerra.repositories.EmployeeRepository.exampleEmployees;

public class EmployeeServiceImpl implements IEmployeeService {

    private final Map<Long, Employee> employees = exampleEmployees();

    @Override
    public Optional<Employee> addEmployee(Employee employee) {
        return Optional.ofNullable(employees.put(employee.getId(), employee));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public boolean deleteEmployee(Long id) {
        return employees.remove(id) != null;
    }
}
