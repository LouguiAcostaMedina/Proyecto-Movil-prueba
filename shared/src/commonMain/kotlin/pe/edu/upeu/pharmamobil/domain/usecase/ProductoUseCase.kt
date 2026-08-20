package pe.edu.upeu.pharmamobil.domain.usecase

import kotlinx.coroutines.delay
import pe.edu.upeu.pharmamobil.domain.model.Producto

class ProductoUseCase {
    suspend fun obtenerProductosSimulados(): List<Producto> {
        delay(1000)
        return listOf(
            Producto(1L, "Paracetamol 500mg", 2.50, 100),
            Producto(2L, "Ibuprofeno 400mg", 3.80, 50),
            Producto(3L, "Amoxicilina 500mg", 12.00, 0)
        )
    }
}
