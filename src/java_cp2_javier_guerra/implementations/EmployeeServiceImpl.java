package java_cp2_javier_guerra.implementations;

import java_cp2_javier_guerra.entities.Employee;
import java_cp2_javier_guerra.interfaces.IEmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements IEmployeeService {

    private final Map<Long, Employee> employees = new HashMap<>();

    @Override
    public void addEmployee(Employee employee) {
        // TODO comprobar que se guarda
        employees.put(employee.getId(), employee);
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
