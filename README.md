# Conversor de Monedas en Java

Este proyecto es un conversor de monedas simple desarrollado en Java. Utiliza la API de ExchangeRate-API para obtener tasas de cambio en tiempo real y permite convertir entre varias monedas. Además, el proyecto incluye funcionalidades adicionales como el historial de conversiones y soporte para más monedas.

## Requisitos

Antes de comenzar, asegúrate de tener instalados los siguientes programas y archivos:

- **Java JDK**: Versión 11 o superior. Puedes descargarlo desde [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **Biblioteca Gson**: Versión 2.10.1 o superior.
- **Postman**: Para probar las API. Descárgalo desde [Postman](https://www.postman.com/downloads/).
- **IDE (Entorno de Desarrollo Integrado)**: Opcionalmente, usa [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

## Configuración del Proyecto

### Dependencias

Asegúrate de tener las siguientes dependencias en tu archivo `build.gradle`:

  ```groovy
  plugins {
      id 'java'
  }
  
  group 'com.example'
  version '1.0-SNAPSHOT'
  
  repositories {
      mavenCentral()
  }
  
  dependencies {
      implementation 'com.squareup.okhttp3:okhttp:4.9.0'
      implementation 'com.google.code.gson:gson:2.10.1'
  }
```

## Estructura del Proyecto
La clase principal del proyecto se encuentra en src/main/java/com/example/CurrencyConverter.java.

## Ejecución del Proyecto
1. Crear el Proyecto:
   * Si estás usando IntelliJ IDEA:
       1. Abre IntelliJ IDEA.
       2. Selecciona File > New > Project.
       3. Selecciona Gradle > Java.
       4. Configura el nombre y la ubicación del proyecto.
       5. Copia y pega el contenido del archivo build.gradle.
       6. Copia y pega el contenido del archivo CurrencyConverter.java en src/main/java/com/example/CurrencyConverter.java.
2. Construir y Ejecutar el Proyecto:
   * En IntelliJ IDEA:
       1. Haz clic derecho en el proyecto y selecciona Reload Gradle Project para descargar las dependencias.
       2. Ejecuta el archivo CurrencyConverter.java haciendo clic derecho sobre él y seleccionando Run 'CurrencyConverter.main()'.

## Uso del Conversor de Monedas
Al ejecutar el programa, se presentará un menú con las siguientes opciones:

1. Convertir moneda
2. Ver historial de conversiones
3. Salir

## Convertir Moneda

* Selecciona la moneda de origen.
* Selecciona la moneda de destino.
* Ingresa el monto a convertir.
* El programa mostrará el monto convertido y registrará la conversión en el historial con una marca de tiempo.

## Ver Historial de Conversiones
* Muestra todas las conversiones realizadas durante la ejecución del programa.

## Salir
* Termina la ejecución del programa.

## Monedas Soportadas
El proyecto soporta las siguientes monedas:

* ARS - Peso argentino
* BOB - Boliviano boliviano
* BRL - Real brasileño
* CLP - Peso chileno
* COP - Peso colombiano
* USD - Dólar estadounidense
* EUR - Euro
* GBP - Libra esterlina
* JPY - Yen japonés

## Contribuciones
Si deseas contribuir a este proyecto, por favor realiza un fork del repositorio, crea una rama con tus cambios y envía un pull request. Todas las contribuciones son bienvenidas.

## Licencia
Este proyecto está bajo la Licencia MIT. 
