package pe.edu.upeu.pharmamobil.domain.usecase

import kotlinx.coroutines.delay
import pe.edu.upeu.pharmamobil.domain.model.Producto

class ProductoUseCase {
    private val productosSimulados = listOf(
        Producto(1L, "Paracetamol", 8.50, 100),
        Producto(2L, "Ibuprofeno", 12.00, 50),
        Producto(3L, "Amoxicilina", 18.50, 20)
    )

    suspend fun obtenerProductos(): List<Producto> {
        delay(1000)
        return productosSimulados
    }
}
