Caso práctico 2
===============

Tema: Gestión de cuentas bancarias
----------------------------------

__Autor__: Javier Guerra  
__Status__: Finalizado  
__Versión__: 1.0.1  
__Fecha__: 2023-02-16  

La aplicación gestiona las cuentas bancarias (`Account`) de un banco (`Bank`).

Cada cuenta bancaria tiene asociado un cliente (`Customer`) y un empleado que creó la cuenta (`Employee`).

Las cuentas bancarias pueden ser de un tipo _enum_ determinado (`AccountType`) y soportar una serie de monedas _enum_ determinadas (`CurrencyType`).

Las cuentas bancarias pueden tener préstamos asociados (`Loan`). Si una cuenta tiene un préstamo asociado, la cuenta no podrá ser borrada. 

Banco, cliente y empleado tienen direcciones asociadas (`Address`).

El banco ofrece una serie de servicios bancarios _enum_ determinados (`BankServices`).

Las entidades `Account`, `Address`, `Customer`, `Employee` y `Loan` tiene programados sus respectivos servicios e implementaciones.

Se utiliza una estructura de datos como base de datos en memoria. Se han creado repositorios que proveen datos demo para cada una de los servicios. Estos repositorios pueden devolver una lista de datos de ejemplo, u opcionalmente, una lista de datos vacía.

La aplicación ofrece un menú por consola cuyas opciones son implementadas en `AccountController`, y solicita datos al empleado que son leídos mediante la clase `Scanner` que es usada en `ConsoleInput`. Esta clase gestiona excepciones.

**Bonus**: La clase `Validation` contiene métodos de validación de NIF, teléfono y correo electrónico que son usados por el método `getFormat` de la clase `ConsoleInput`.


Opciones del menú:

* Listar todas las cuentas
* Buscar una cuenta por su id
* Buscar una cuenta por el NIF del cliente
* Buscar las cuentas por el tipo de cuenta
* Buscar las cuentas por una moneda soportada
* Listar tipos de cuenta y sus cuentas relacionadas (a través de un Map)  
* Crear una nueva cuenta
* Incrementar el saldo de una cuenta por su id
* Retirar saldo de una cuenta por su id
* Actualizar una cuenta por su id
* Borrar una cuenta por su id
* Transferir saldo entre dos cuentas por sus id
* Salir de la aplicación

Cada opción realiza una serie de comprobaciones, generando los correspondientes mensajes de error, antes de realizar, o no, la operación asociada.

Se genera la documentación de la aplicación con `JavaDoc` en la carpeta: __doc__.

Se incluye `CHANGELOG.md` con los cambios de versión.  

## Consideraciones

Para la realización del ejercicio, se considera que:  

- La aplicación sólo gestiona las opciones relativas a cuentas bancarias.  
- Hay un único banco, y todos los servicios están asociados a él.  
- Hay un único cliente por cuenta bancaria.
- Existe al menos un empleado que gestiona las opciones de cuentas bancarias.  

## Licencias

Sobre el código fuente: [GNU GENERAL PUBLIC LICENSE Version 3](LICENSE)

## Saber más

Artículos informativos elaborados por el autor relacionados con esta práctica:

- [«ConsoleInput», una clase en Java para lectura de datos por consola.](https://javguerra.github.io/2023-01-31-clase-scanner-java/) 
