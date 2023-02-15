package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Employee;

import java.util.HashMap;
import java.util.Map;

public final class EmployeeRepository {

    private Map<Long, Employee> employees = new HashMap<>();

    public EmployeeRepository(Boolean data) {
        setEmployees(exampleEmployees(data));
    }

    public Map<Long, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Long, Employee> employees) {
        this.employees = employees;
    }

    /**
     * Genera una lista de empleados de ejemplo o una lista vacía.
     * @param data true incluye datos de ejemplo, false no lo hace.
     * @return Lista de empleados.
     */
    private Map<Long, Employee> exampleEmployees(Boolean data) {
        if (data == null) data = false;

        Map<Long, Employee> employees = new HashMap<>();

        if (data) {
            var employee1 = new Employee(1L, "Empleado 1", "87654321A", 2L, "+34 123 456 000", "empleado1@banco.es");

            employees.putAll(Map.of(employee1.getId(), employee1));
        }

        return employees;
    }
}
