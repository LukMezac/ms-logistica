# Microservicio de Logística - Proyecto Donaton

Este repositorio contiene el microservicio de **Logística**, un componente especializado del ecosistema **Donaton** desarrollado con **Spring Boot 3.x**. Su función principal es gestionar la cadena de distribución, el seguimiento de envíos y la asignación de transporte para las donaciones procesadas.

## 🏗️ Arquitectura y Patrones de Diseño
Este microservicio ha sido construido bajo los estándares de escalabilidad y eficiencia exigidos en la evaluación:

*   **📦 Arquetipo Maven**: Implementación basada en un arquetipo estandarizado para asegurar la coherencia técnica y estructural en todo el backend.
*   **📂 Repository Pattern**: Uso de **Spring Data JPA** para desacoplar la lógica de negocio de la capa de persistencia, facilitando el mantenimiento.
*   **🌐 Microservices Architecture**: Diseño orientado a servicios independientes, permitiendo que la lógica de transporte y envíos escale según la demanda del sistema.

## 🛠️ Tecnologías Utilizadas
*   **☕ Java 17**: Lenguaje de programación robusto para el manejo de lógica de distribución compleja.
*   **🍃 Spring Boot 3.x**: Framework principal para el desarrollo de servicios ágiles y de fácil configuración.
*   **📊 Spring Data JPA**: Para una gestión de datos y persistencia de estados de envío eficiente.
*   **🔨 Maven**: Herramienta de gestión de dependencias y estandarización del proyecto.

## 📂 Estructura del Componente
Siguiendo el formato de entrega solicitado, el código se organiza de la siguiente manera:
*   `controller/`: Endpoints REST que permiten la comunicación y actualización de estados desde el BFF.
*   `service/`: Capa de lógica de negocio encargada de la orquestación de envíos.
*   `repository/`: Interfaces para la persistencia de datos de logística.
*   `model/`: Entidades de negocio que representan los envíos y su información asociada.

## 🚀 Instalación y Ejecución

1.  **📋 Requisitos**:
    *   Java 17 instalado.
    *   Maven 3.8+.

2.  **⚙️ Configuración**:
    El servicio está configurado para operar de forma predeterminada en el puerto `8082`. Estos parámetros pueden ajustarse en `src/main/resources/application.properties`.

3.  **▶️ Ejecución**:
    ```bash
    mvn spring-boot:run
    ```

## 🧪 Pruebas y Calidad
*   **✅ Pruebas Unitarias**: El proyecto incluye validaciones unitarias para garantizar que el componente backend sea eficiente y mantenible.
*   **🧹 Clean Code**: Se aplica una nomenclatura clara y una estructura ordenada para facilitar la colaboración en equipo y el control de versiones.

---
