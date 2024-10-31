# Xifrador factory

El fichero Main para realizar las pruebas del proyecto se encuentra en [src/test/java/](src/test/java/TestXifratge.java).

## Estructura de las interfaces

### Xifrador
`Xifrador` es una interfaz que define los métodos necesarios para cifrar y descifrar mensajes. Los métodos incluidos son:
- `TextXifrat xifra(String msg, String clau) throws ClauNoSuportada`: Cifra un mensaje utilizando una clave.
- `String desxifra(TextXifrat msg, String clau) throws ClauNoSuportada`: Descifra un mensaje cifrado utilizando una clave.

### AlgorismeFactory
`AlgorismeFactory` es una clase abstracta que define un método para crear instancias de `Xifrador`. El método incluido es:
- `Xifrador creaXifrador()`: Método abstracto que debe ser implementado para devolver una instancia de `Xifrador`.



