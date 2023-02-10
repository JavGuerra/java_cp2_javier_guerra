package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    /**
     *
     * @param employee
     * @return
     */
    Optional<Employee> addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Long id);
}
