package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    /**
     * Añade un empleado a la lista de empleados.
     * @param employee empleado a añadir.
     * @return opcionalmente, el mismo empleado.
     */
    Optional<Employee> addEmployee(Employee employee);

    /**
     * Obtiene todos los empleados.
     * @return lista de empleados.
     */
    List<Employee> getAllEmployees();

    /**
     * Borra un empleado de la lista.
     * @param id del empleado.
     * @return true si se borró el empleado o false en caso contrario.
     */
    boolean deleteEmployee(Long id);
}
