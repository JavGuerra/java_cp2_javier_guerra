package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Employee;

import java.util.HashMap;
import java.util.Map;

public final class EmployeeRepository {

    private Map<Long, Employee> employees = new HashMap<>();

    public EmployeeRepository() {
        setEmployees(exampleEmployees());
    }

    public Map<Long, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Long, Employee> employees) {
        this.employees = employees;
    }

    private Map<Long, Employee> exampleEmployees() {
        var employee1 = new Employee(1L,"Empleado 1", "87654321A", 2L);

        return new HashMap<>(Map.of(employee1.getId(), employee1));
    }
}
