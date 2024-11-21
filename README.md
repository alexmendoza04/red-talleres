# Extractor de Deducibles de Taller

Este proyecto es una aplicación basada en Spring Boot que permite extraer y procesar deducibles de talleres a partir de un payload JSON.

## Características
- Extracción de deducibles a partir de datos JSON.
- Arquitectura basada en servicios con componentes desacoplados.
- Tests automatizados para validar el procesamiento de deducibles.

## Requisitos Previos
- **Java 8 o superior**
- **Maven 3.6.3 o superior**

## Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/usuario/ExtractorDeduciblesTaller.git
   ```
2. Navegar al directorio del proyecto:
	```bash
	cd ExtractorDeduciblesTaller
	```
3. Construir el proyecto:
	```bash
	mvn clean install
	```

## Ejecución
* Ejecutamos el siguiente comando
	```bash
	mvn spring-boot:run
	```
* La aplicación estará disponible en http://localhost:8080.

## Estructura del Proyecto

* src/main/java - Código fuente principal.
* src/test/java - Pruebas automatizadas.
* pom.xml - Configuración de dependencias Maven.

## Uso
Puedes probar los endpoints usando herramientas como Postman o curl. Los endpoints principales están definidos en DeductibleController.