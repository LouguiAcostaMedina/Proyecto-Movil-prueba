# PharmaMobile

Aplicación móvil multiplataforma para la gestión integral de clientes, productos, pedidos e inventario farmacéutico.

## Características y Arquitectura
* **Tecnología:** Kotlin Multiplatform (KMP) y Compose Multiplatform.
* **Enfoque:** Estrategia *offline-first* y consumo de servicios REST[cite: 1].

## Estructura del Código
* `commonMain`: Contiene la lógica de negocio, reglas y modelos compartidos[cite: 1].
* `androidMain`: Código y librerías nativas específicas para Android[cite: 1].
* `iosMain`: Código y vinculación nativa para la plataforma iOS[cite: 1].