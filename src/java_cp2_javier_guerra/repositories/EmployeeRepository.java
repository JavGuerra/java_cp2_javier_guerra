package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {

    public static Map<Long, Employee> exampleEmployees() {

        Employee employee1 = new Employee(1L,"Empleado 1", "87654321A", 2L);

        return new HashMap<>(Map.of(employee1.getId(), employee1));
    }
}
