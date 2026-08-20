package pe.edu.upeu.pharmamobil.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pe.edu.upeu.pharmamobil.domain.model.Producto
import pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos

class ProductoFlowUseCase {
    fun observarProductos(): Flow<ResultadoProductos> = flow {
        emit(ResultadoProductos.Cargando)
        delay(1500)
        val productos = listOf(
            Producto(1L, "Paracetamol 500mg", 2.50, 100),
            Producto(2L, "Ibuprofeno 400mg", 3.80, 50)
        )
        emit(ResultadoProductos.Exito(productos))
    }
}
