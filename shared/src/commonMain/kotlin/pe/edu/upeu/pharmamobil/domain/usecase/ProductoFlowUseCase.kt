package pe.edu.upeu.pharmamobil.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pe.edu.upeu.pharmamobil.domain.model.Producto
import pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos

class ProductoFlowUseCase {
    private val productosSimulados = listOf(
        Producto(1L, "Paracetamol", 8.50, 100),
        Producto(2L, "Ibuprofeno", 12.00, 50),
        Producto(3L, "Amoxicilina", 18.50, 20)
    )

    fun observarProductos(): Flow<List<Producto>> = flow {
        emit(emptyList())
        delay(1000)
        emit(
            productosSimulados.map { producto ->
                if (producto.id == 1L) producto.copy(stock = 90) else producto
            }
        )
    }

    fun cargarProductosEstado(): Flow<ResultadoProductos> = flow {
        emit(ResultadoProductos.Cargando)
        delay(1000)
        emit(ResultadoProductos.Exito(productosSimulados))
    }
}
