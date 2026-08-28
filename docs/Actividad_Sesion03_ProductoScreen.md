# PORTADA

**UNIVERSIDAD PRIVADA DE EDUCACIÓN**

Facultad de Ingeniería de Sistemas

---

**Asignatura:** Desarrollo de Aplicaciones Móviles

**Tema:** Compose Multiplatform y Arquitectura Declarativa

**Sesión 03:** Construcción de Formulario Interactivo – Módulo de Productos

| Campo | Valor |
|---|---|
| **Estudiante:** | [Nombre del Estudiante] |
| **Código:** | [Código del Estudiante] |
| **Sección:** | [Sección / Grupo] |
| **Docente:** | [Nombre del Docente] |
| **Fecha de entrega:** | 26 de agosto de 2026 |

---

# ÍNDICE

1. Introducción
2. Objetivos
3. Desarrollo
4. Evidencias
   - 4.1 Estructura del proyecto
   - 4.2 Interfaz funcionando
   - 4.3 Validación incorrecta
   - 4.4 Código fuente de ProductoScreen.kt
5. Reflexión de la Práctica
6. Conclusiones
7. Guía de Pruebas Post-Desarrollo y Checklist (Anexo)

---

## 1. Introducción

En la sesión 03 se abordó la construcción del módulo de presentación del sistema de inventario PharmaMobil, enfocado en el registro interactivo de productos farmacéuticos. Siguiendo el paradigma declarativo que sustenta Compose Multiplatform, la interfaz gráfica se definió como una función pura del estado: `UI = f(Estado)`. Cada componente visual — campos de entrada, botón de acción y tarjetas de retroalimentación — se declaró de forma atómica dentro de un árbol de composición, mientras que el estado mutable (`mutableStateOf`) gestionó la reactividad ante las interacciones del usuario. Esta aproximación eliminó la necesidad de manipular el DOM imperativamente, delegando al runtime de Compose la sincronización automática entre los datos y su representación visual.

---

## 2. Objetivos

| N° | Taxonomía | Objetivo |
|---|---|---|
| 1 | **Comprender** | Los fundamentos del paradigma declarativo en Compose Multiplatform y su diferencia con el enfoque imperativo tradicional. |
| 2 | **Aplicar** | Los componibles `@Composable`, `Column`, `Modifier`, `Text`, `OutlinedTextField`, `Button`, `remember` y `mutableStateOf` para construir formularios reactivos. |
| 3 | **Analizar** | El ciclo de vida de los estados reactivos y la importancia de las conversiones seguras (`toDoubleOrNull`, `toIntOrNull`) en la validación de datos de entrada. |
| 4 | **Crear** | Un formulario funcional de registro de productos que valide datos, instancie un objeto de dominio y emita retroalimentación visual al usuario. |

---

## 3. Desarrollo

### 3.1 Estructura de los 12 pasos de la guía

La implementación de `ProductoScreen.kt` siguió estrictamente los 12 pasos definidos en la Guía Práctica de la Sesión 03:

| Paso | Descripción | Implementación |
|---|---|---|
| 1 | Declarar el composable `ProductoScreen()` | Función anotada con `@Composable` y `@OptIn(ExperimentalMaterial3Api::class)` |
| 2 | Estado: `nombre` | `var nombre by remember { mutableStateOf("") }` |
| 3 | Estado: `precio` | `var precio by remember { mutableStateOf("") }` |
| 4 | Estado: `stock` | `var stock by remember { mutableStateOf("") }` |
| 5 | Estado: `mensaje` | `var mensaje by remember { mutableStateOf("") }` |
| 6 | Layout raíz con `Column` | `Column` dentro de un `Scaffold` con `fillMaxSize`, `padding` y `verticalScroll` |
| 7 | Campo de nombre | `OutlinedTextField` con `label`, `placeholder` y `singleLine = true` |
| 8 | Campo de precio | `OutlinedTextField` con label "Precio unitario (S/)" |
| 9 | Campo de stock | `OutlinedTextField` con label "Stock disponible" |
| 10 | Validar nombre no vacío | `if (nombre.isBlank())` → mensaje "Ingrese nombre" |
| 11 | Validar precio numérico > 0 | `precio.toDoubleOrNull()` con verificación `<= 0.0` |
| 12 | Validar stock entero ≥ 0 | `stock.toIntOrNull()` con verificación `< 0` |

### 3.2 Jerarquía visual

```
Scaffold
├── TopAppBar  (título "Registro de Producto")
└── Column  (fillMaxSize, padding 16dp, verticalScroll)
    ├── Text  (encabezado descriptivo)
    ├── HorizontalDivider
    ├── OutlinedTextField  (nombre)
    ├── OutlinedTextField  (precio)
    ├── OutlinedTextField  (stock)
    ├── Spacer
    ├── Button  ("Registrar Producto")
    └── Card  (mensaje de éxito o error, condicional)
```

### 3.3 Gestión de estados

El estado de la UI se gestionó mediante `remember { mutableStateOf(...) }` para cada campo. Cuando el usuario modifica un campo, `onValueChange` actualiza la variable vinculada, lo que dispara una recomposición selectiva de los componibles que dependen de ese estado. El botón de acción orquesta la validación secuencial y, solo si todas pasan, instancia el objeto `Producto` del dominio.

---

## 4. Evidencias

### Evidencia 01: Estructura del proyecto

**[INSTRUCCIÓN PARA EL ESTUDIANTE: Insertar aquí la captura de pantalla del árbol de directorios en Android Studio mostrando la ruta `presentation/producto/ProductoScreen.kt`. Para obtenerla, en Android Studio, haga clic derecho en la carpeta `presentation` → "Show in Explorer" o expanda el árbol en la vista "Project" hasta llegar al archivo.]**

Ruta exacta del archivo:

```
shared/src/commonMain/kotlin/pe/edu/upeu/pharmamobil/presentation/producto/ProductoScreen.kt
```

### Evidencia 02: Interfaz funcionando

**[INSTRUCCIÓN PARA EL ESTUDIANTE: Insertar aquí la captura de pantalla del emulador o la previa en Android Studio mostrando el formulario completo con los datos del Caso 01 llenados: Nombre = "Paracetamol", Precio = "8.50", Stock = "100", y el mensaje de éxito visible en la tarjeta inferior.]**

Datos de entrada para esta captura:

| Campo | Valor |
|---|---|
| Nombre del producto | Paracetamol |
| Precio unitario | 8.50 |
| Stock disponible | 100 |
| Mensaje esperado | Producto registrado: Paracetamol - S/8.5 - Stock: 100 |

### Evidencia 03: Validación incorrecta

**[INSTRUCCIÓN PARA EL ESTUDIANTE: Insertar aquí la captura de pantalla mostrando un mensaje de error visualizado en el formulario. Puede replicar el Caso 03 (Precio "abc") o el Caso 04 (Stock "-10") según su preferencia.]**

Opciones de captura:

- **Caso 03:** Ingresar "abc" en el campo Precio → Mensaje esperado: "Ingrese precio válido"
- **Caso 04:** Ingresar "-10" en el campo Stock → Mensaje esperado: "Stock no puede ser negativo"

### Evidencia 04: Código fuente de ProductoScreen.kt

```kotlin
package pe.edu.upeu.pharmamobil.presentation.producto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.upeu.pharmamobil.domain.model.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoScreen() {
    // Estado de la UI: variables reactivas para cada campo del formulario
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var esExito by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Registro de Producto",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Encabezado descriptivo
            Text(
                text = "Ingrese los datos del producto",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            HorizontalDivider()

            // Campo: Nombre del producto
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del producto") },
                placeholder = { Text("Ej: Paracetamol") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Campo: Precio unitario
            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio unitario (S/)") },
                placeholder = { Text("Ej: 8.50") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Campo: Stock disponible
            OutlinedTextField(
                value = stock,
                onValueChange = { stock = it },
                label = { Text("Stock disponible") },
                placeholder = { Text("Ej: 100") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón de registro
            Button(
                onClick = {
                    // PASO 10: Validar nombre no vacío
                    if (nombre.isBlank()) {
                        mensaje = "Ingrese nombre"
                        esExito = false
                        return@Button
                    }

                    // PASO 11: Validar precio numérico mayor a cero (conversión segura)
                    val precioDouble = precio.toDoubleOrNull()
                    if (precioDouble == null || precioDouble <= 0.0) {
                        mensaje = "Ingrese precio válido"
                        esExito = false
                        return@Button
                    }

                    // PASO 12: Validar stock entero no negativo (conversión segura)
                    val stockInt = stock.toIntOrNull()
                    if (stockInt == null || stockInt < 0) {
                        mensaje = "Stock no puede ser negativo"
                        esExito = false
                        return@Button
                    }

                    // Instanciar el objeto Producto con los datos validados
                    val producto = Producto(
                        id = System.currentTimeMillis(),
                        nombre = nombre.trim(),
                        precio = precioDouble,
                        stock = stockInt
                    )

                    // Confirmación exitosa
                    mensaje = "Producto registrado: ${producto.nombre} - S/${producto.precio} - Stock: ${producto.stock}"
                    esExito = true

                    // Limpiar campos después del registro exitoso
                    nombre = ""
                    precio = ""
                    stock = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Registrar Producto",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Mensaje de resultado (éxito o error)
            if (mensaje.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (esExito)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = mensaje,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        color = if (esExito)
                            MaterialTheme.colorScheme.onPrimaryContainer
                        else
                            MaterialTheme.colorScheme.onErrorContainer,
                        fontWeight = if (esExito) FontWeight.Bold else FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
```

---

## 5. Reflexión de la Práctica

### Pregunta 1: ¿Por qué Compose utiliza programación declarativa en lugar del enfoque imperativo tradicional?

Compose adopta el paradigma declarativo porque separa la **descripción** de la interfaz de la **manipulación** de la interfaz. En el enfoque imperativo (como Android Views tradicional), el desarrollador debe ejecutar secuencias procedurales: encontrar un componente visual por su identificador, modificar sus propiedades y gestionar manualmente la sincronización con el estado de la aplicación. Esto genera código propenso a errores de inconsistencia entre el estado lógico y su representación visual.

En contraste, el enfoque declarativo define **qué** debe mostrarse en función del estado actual (`UI = f(Estado)`), sin especificar **cómo** llegar a ese estado. El runtime de Compose se encarga de calcular el árbol de diferencias (recomposición) y aplicar solo los cambios necesarios en el sistema de renderizado subyacente. Esto produce código más conciso, más predecible y con menos superficie de error, ya que no existen estados intermedios inconsistentes entre la lógica de negocio y la capa visual. Además, facilita la composición de interfaces a partir de pequeños componentes reutilizables, lo que mejora la mantenibilidad y escalabilidad del código.

### Pregunta 2: ¿Qué sucede exactamente a nivel de ciclo de vida cuando cambia una variable creada con `mutableStateOf`?

Cuando se modifica el valor de un estado creado con `mutableStateOf`, el sistema de Compose ejecuta los siguientes pasos:

1. **Emisión de valor:** La asignación del nuevo valor actualiza el objeto `MutableState<T>`, que internamente notifica a todos los **recomposers** activos (scope) que están leyendo ese estado.
2. **Invalidación del recomposer:** El runtime de Compose marca como "inválidos" los scopes de recomposición que capturaron (leyeron) ese estado específico durante su última ejecución. Esto se implementa mediante un mecanismo de observación basado en `Snapshot` del runtime.
3. **Recomposición diferencial:** En un ciclo de recorrido (traversal) posterior, Compose recorre el árbol de composición y re-ejecuta únicamente las funciones `@Composable` cuyos scopes fueron invalidados, comparando los parámetros de entrada y evitando re-ejecutar subárboles estables.
4. **Actualización del host:** Solo los nodos del sistema de renderizado (Android Views, Skia, etc.) cuyo resultado de composición cambió reciben las instrucciones de actualización correspondientes.

Es importante destacar que la recomposición **no es inmediata**; se difiere hasta el siguiente frame, y Compose puede comprimir (coalesce) múltiples cambios de estado en una sola recomposición para optimizar el rendimiento.

### Pregunta 3: ¿Por qué es importante validar la información antes de instanciar y emitir un objeto Producto?

Validar antes de instanciar es crucial por tres razones fundamentales:

1. **Integridad del modelo de dominio:** El objeto `Producto` tiene un contrato implícito: `precio > 0` y `stock >= 0`. Si se permitiera instanciar con valores inválidos, propagaría datos corruptos a capas superiores (base de datos, red, lógica de negocio), generando comportamientos impredecibles como precios negativos o stock imposible.

2. **Seguridad en las conversiones de tipo:** El formulario recibe texto libre del usuario. Sin validación previa, una llamada directa a `.toDouble()` sobre una cadena alfanumérica (como "abc") lanzaría una `NumberFormatException`, provocando el cierre inesperado de la aplicación. Las funciones `toDoubleOrNull()` y `toIntOrNull()` devuelven `null` en lugar de lanzar excepciones, permitiendo un manejo controlado del error.

3. **Experiencia de usuario:** La validación anticipada permite emitir mensajes descriptivos y específicos al usuario (ej. "Ingrese precio válido"), permitiéndole corregir el error antes de que el sistema rechace la operación silenciosamente o crashee.

### Pregunta 4: ¿Qué ventajas arquitectónicas y de mantenimiento tiene dividir una pantalla compleja en pequeños componentes composables?

La división en componentes atómicos ofrece ventajas significativas:

- **Recomposición granular:** Cuando cambia el estado de un campo específico, solo el composable que depende de ese estado se recompone, no toda la pantalla. Esto reduce el costo computacional y mejora la fluidez de la interfaz.

- **Reutilización:** Un componente como `FormField(label, value, onValueChange)` puede reutilizarse en múltiples pantallas sin duplicación de código, siguiendo el principio DRY.

- **Testeabilidad:** Los componentes aislados pueden testearse de forma independiente, tanto visualmente (con Preview) como funcionalmente (con tests de UI).

- **Legibilidad:** Cada función `@Composable` encapsula una responsabilidad visual única, lo que facilita la comprensión del código, la incorporación de nuevos desarrolladores y la depuración de errores visuales.

- **Mantenibilidad:** Los cambios en un componente no afectan a otros, reduciendo el riesgo de regresiones y haciendo que el código sea más resiliente a cambios de requisitos.

### Pregunta 5: ¿Cómo podría mejorar esta implementación aplicando el patrón arquitectónico MVVM?

La implementación actual concentra toda la lógica (estado, validación, instanciación) en el propio composable. Aplicando MVVM se separarían las responsabilidades:

- **Model (M):** El data class `Producto` existente cumple este rol. Se agregarían clases de caso de uso o repositorio para persistencia.

- **ViewModel (VM):** Se crearía `ProductoViewModel` con un estado expuesto como `StateFlow<ProductoUiState>` encapsulando los campos del formulario, el mensaje y el flag de éxito. La validación se ejecutaría en métodos del ViewModel, no en el `onClick` del botón. Ejemplo:

```kotlin
class ProductoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProductoUiState())
    val uiState: StateFlow<ProductoUiState> = _uiState.asStateFlow()

    fun onNombreChanged(valor: String) {
        _uiState.update { it.copy(nombre = valor) }
    }

    fun registrar() {
        val state = _uiState.value
        // Validaciones centralizadas...
    }
}
```

- **View (V):** El composable `ProductoScreen` solo observaría el `uiState` y delegaría las acciones al ViewModel. No contendría lógica de validación ni de negocio.

Esta separación facilita el testing unitario del ViewModel sin dependencia de UI, la reutilización del ViewModel en diferentes plataformas (Android, Desktop, iOS) y la incorporación de herramientas como `SavedStateHandle` para sobrevivir a cambios de configuración.

---

## 6. Conclusiones

1. **Transformación del modelo de dominio en interfaz gráfica:** La sesión permitió demostrar que un data class de dominio (`Producto`) puede representarse fielmente en una interfaz gráfica mediante Compose, estableciendo un flujo unidireccional de datos: el usuario ingresa texto, el estado reactivo captura los cambios, la validación garantiza la integridad, y la instanciación produce un objeto de negocio coherente. Este patrón confirma que el paradigma declarativo facilita la traducción directa de modelos conceptuales en interfaces funcionales, reduciendo la brecha entre el diseño y la implementación.

2. **Validación robusta con conversiones seguras:** El uso de `toDoubleOrNull()` y `toIntOrNull()` demostró ser una estrategia efectiva para manejar la naturaleza impredecible de la entrada de usuario en formularios. A diferencia de las conversiones directas (`.toDouble()`), estas funciones alternativas eliminan el riesgo de excepciones no controladas, permitiendo un manejo elegante de errores que mejora tanto la estabilidad de la aplicación como la experiencia del usuario, al ofrecer retroalimentación descriptiva en lugar de fallos del sistema.

---

## 7. Guía de Pruebas Post-Desarrollo y Checklist (Anexo para el Estudiante)

### Verificación de las 4 imágenes

- [ ] **Evidencia 01 insertada:** Captura del árbol de directorios mostrando `presentation/producto/ProductoScreen.kt`
- [ ] **Evidencia 02 insertada:** Captura del emulador con el formulario llenado (Caso 01: Paracetamol, 8.50, 100) y mensaje de éxito visible
- [ ] **Evidencia 03 insertada:** Captura con un mensaje de error de validación (Caso 03 o Caso 04)

### Verificación de los 4 casos de prueba

Ejecute la aplicación en el emulador o dispositivo y verifique que los siguientes casos producen los resultados indicados:

| Caso | Campo(s) a ingresar | Entrada(s) | Resultado esperado | [ ] OK |
|---|---|---|---|---|
| 01: Producto correcto | Nombre / Precio / Stock | Paracetamol / 8.50 / 100 | Mensaje de éxito: "Producto registrado: Paracetamol - S/8.5 - Stock: 100" y campos limpiados | [ ] |
| 02: Nombre vacío | Nombre (dejar vacío) | (vacío) / 8.50 / 100 | Mensaje de error: "Ingrese nombre" | [ ] |
| 03: Precio alfanumérico | Precio | Paracetamol / abc / 100 | Mensaje de error: "Ingrese precio válido" | [ ] |
| 04: Stock negativo | Stock | Paracetamol / 8.50 / -10 | Mensaje de error: "Stock no puede ser negativo" | [ ] |

### Checklist de formato general para Word

- [ ] Portada completa con todos los campos de identificación
- [ ] Índice con todas las secciones numeradas
- [ ] Secciones 1 a 6 redactadas y formateadas correctamente
- [ ] Código fuente de Evidencia 04 pegado con formato de fuente monoespaciada (Consolas o Courier New, tamaño 9-10)
- [ ] Tablas formateadas con bordes visibles
- [ ] Ortografía y gramática revisadas
- [ ] Documento guardado en formato `.docx`
