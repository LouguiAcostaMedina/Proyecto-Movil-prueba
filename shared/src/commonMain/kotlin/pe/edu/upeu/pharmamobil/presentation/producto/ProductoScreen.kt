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
                        id = kotlin.random.Random.nextLong(1, 10000),
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
