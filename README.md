Caso práctico 2
===============

Tema: Gestión de cuenta bancaria
--------------------------------

__Autor__: Javier Guerra  
__Status__: Pendiente  
__Versión__: 1.0.0  
__Fecha__: 2023-02-

## Clases

Clases OBLIGATORIAS:
* Clase principal **BankAccount**:
    * Identificador (numérico)
    * Saldo (numérico)
    * Tipo (enum): ahorro, inversiones, empresa
    * Coste (numérico)
    * Interés/recomensa (numérico)
    * Customer (Otra clase **obligatoria**)
    * Lista de monedas soportadas: EUR, USD, FOR, HUG, CZK, YEN (Set o List de String)
    * Fecha de creación
* Clase **Customer**:
    * id
    * nif
    * name

Clases OPCIONALES:
* Clase Banco (asociado a BankAccount)
* Clase Address (asociado a Customer)
* Clase Préstamo: account, Empleado, cantidad, interés, fecha expiración
* Clase Empleado
* Interfaz e implementación para cada clase

## Creación de interfaz

Crear una interfaz para las operaciones de cuenta bancaria:
* Buscar todas las cuentas bancarias
* Buscar una cuenta bancaria por su id
* Buscar una cuenta bancaria por el nif de su usuario/cliente
* Buscar todas las cuentas bancarias por el tipo de cuenta. Ejemplo: buscar cuentas de tipo ahorro devuelve una lista con las cuentas de ahorro.
* Buscar por moneda soportada
* Agrupar por tipo de cuenta: devuelve un mapa cuyas claves son los tipos de cuenta y cuyos valores son listas de cuentas de esos tipos. Aquí se devuelve un mapa con todos los tipos de cuenta pero agrupados.
* Crear nueva cuenta bancaria
* Incrementar saldo
* Hacer retiro / Disminuir saldo
* Actualizar cuenta bancaria
* Borrar cuenta bancaria
* Traspasar saldo de una cuenta a otra

## Crear implementación de la interfaz que implemente todas las operaciones

## Consola:
* Mostrar un menú por consola con todas las opciones de la interfaz
* El usuario puede elegir una opción
* Leer de consola los datos necesarios para ejecutar la operación que haya elegido

## Recomendable:
* Intentar gestionar las posibles excepciones y repetir las lecturas en caso de que el usuario introduzca mal los datos. Se puede crear algo parecido a la clase ScannerReader vista en el curso.


## TODO
- Revisar los datos de entrada de todos los métodos
- Listar sólo cuentas activas
- Validar Nif del banco, correos y dni
- Generar ID automático en entidades
- Clase Préstamo y servicio asociado

Se incluye `CHANGELOG.md` con los cambios de versión.

## Licencias

Sobre el código fuente: [GNU GENERAL PUBLIC LICENSE Version 3](LICENSE)

## Saber más

Artículos informativos elaborados por el autor relacionados con esta práctica:

- [«ConsoleInput», una clase en Java para lectura de datos por consola.](https://javguerra.github.io/2023-01-31-clase-scanner-java/) 
