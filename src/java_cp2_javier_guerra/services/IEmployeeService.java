package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Employee;

import java.util.List;

public interface IEmployeeService {

    void addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Long id);
}
