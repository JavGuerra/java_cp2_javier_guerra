Caso práctico 2
===============

Tema: Gestión de cuenta bancaria
--------------------------------

__Autor__: Javier Guerra  
__Status__: Finalizado  
__Versión__: 1.0.0  
__Fecha__: 2023-02-15  

La aplicación gestiona las cuentas bancarias (`Account`) de un banco (`Bank`).

Las cuentas bancarias tienen asociados clientes (`Customer`) y empleado que genera la cuenta (`Employee`).

Las cuentas bancarias pueden ser de un tipo enum (`AccountType`) determinado y soportar una serie de monedas enum (`CurrencyType`) determinadas.

Las cuentas bancarias pueden tener préstamos (`Loan`) asociados. Si una cuenta tiene un préstamo asociado, la cuenta no podrá ser borrada. 

Banco, cliente y empleado tienen direcciones (`Address`) asociadas.

El banco ofrece una serie de servicios bancarios enum (`BankServices`) determinados.

Las entidades `Account`, `Address`, `Customer`, `Employee` y `Loan` cuentan con sus respectivos servicios e implementaciones.

Se utiliza una estructura de datos como base de datos en memoria. Se han creado repositorios que proveen datos demo para cada una de estas cuentas. Estos repositorios pueden devolver, opcionalmente, una lista de datos o una lista de datos vacía.

La aplicación ofrece un menú por consola cuyas opciones son implementadas en `AccountController`, y solicita datos al usuario que son leídos mediante la clase `Scanner` que es usada en la clase `ConsoleInput`, clase que gestiona excepciones.

Opciones del menú:

* Listar todas las cuentas
* Buscar una cuenta por su id
* Buscar una cuenta por el NIF del usuario
* Buscar las cuentas por el tipo de cuenta
* Buscar las cuentas por una moneda soportada
* Listar un tipo de cuenta y cuentas relacionadas
* Crear una nueva cuenta
* Incrementar el saldo de una cuenta por su id
* Retirar saldo de una cuenta por su id
* Actualizar una cuenta por su id
* Borrar una cuenta por su id
* Transferir saldo entre dos cuentas por sus id
* Salir de la aplicación

Cada opción realiza una serie de comprobaciones antes de realizar la operación asociada.

Se genera la documentación de la aplicación con `JavaDoc` en la carpeta doc.

Se incluye `CHANGELOG.md` con los cambios de versión.  

## Consideraciones

Para el ejercicio, se considera que:  

- Hay un único banco, y todos los servicios están asociados a él.  
- Existe al menos un empleado que gestiona las cuentas bancarias. 
- La aplicación sólo gestiona las opciones relativas a cuentas bancarias.

## TODO

- Validar las entradas: NIF del banco, DNI de los clientes y empleados, y cuentas de correo.  

## Licencias

Sobre el código fuente: [GNU GENERAL PUBLIC LICENSE Version 3](LICENSE)

## Saber más

Artículos informativos elaborados por el autor relacionados con esta práctica:

- [«ConsoleInput», una clase en Java para lectura de datos por consola.](https://javguerra.github.io/2023-01-31-clase-scanner-java/) 
