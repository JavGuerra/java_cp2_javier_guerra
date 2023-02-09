package java_cp2_javier_guerra;

import java_cp2_javier_guerra.entities.*;
import java_cp2_javier_guerra.enums.*;
import java_cp2_javier_guerra.implementations.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExampleData {

    public static Bank exampleBank() {

        var employee1 = new Employee(1L,"Empleado 1", "87654321A");

        var address1 = new Address(1L, "Dirección 1", "04341", "Madrid", "España");
        var address2 = new Address(2L, "Dirección 2", "04342", "Madrid", "España");
        var address3 = new Address(3L, "Dirección 3", "04343", "Barcelona", "España");

        var customer1 = new Customer(1L, "Cliente 1", "12345678A", address1);
        var customer2 = new Customer(2L, "Cliente 2", "12345678B", address2);
        var customer3 = new Customer(3L, "Cliente 3", "12345678C", address3);

        var currencies1 = new HashSet<>(Set.of(CurrencyType.EUR, CurrencyType.USD));
        var currencies2 = new HashSet<>(Set.of(CurrencyType.JPY, CurrencyType.USD));
        var currencies3 = new HashSet<>(Set.of(CurrencyType.CZK, CurrencyType.CHF, CurrencyType.RUB));

        var account1 = new Account(1L, 50000d, BankAccountType.BUSINESS,  currencies1, customer1.getId(), employee1.getId());
        var account2 = new Account(2L, 5000d, BankAccountType.INVESTMENT,  currencies2, customer2.getId(), employee1.getId());
        var account3 = new Account(3L, 10000d, BankAccountType.BUSINESS,  currencies3, customer3.getId(), employee1.getId());

        var employeeService = new EmployeeServiceImpl();
        employeeService.addEmployee(employee1);

        var addressService = new AddressServiceImpl();
        addressService.addAddress(address1);
        addressService.addAddress(address2);
        addressService.addAddress(address3);

        var customerService = new CustomerServiceImpl();
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);
        customerService.addCustomer(customer3);

        var accountService = new AccountServiceImpl();
        accountService.addAccount(account1);
        accountService.addAccount(account2);
        accountService.addAccount(account3);

        Map<String, Object> services = new HashMap<>();
        services.put("accountService", accountService);
        services.put("customerService", customerService);
        services.put("employeeService", employeeService);
        services.put("addressService", addressService);

        return new Bank(1L, "Banco 1", "11223344B", services);
    }
}