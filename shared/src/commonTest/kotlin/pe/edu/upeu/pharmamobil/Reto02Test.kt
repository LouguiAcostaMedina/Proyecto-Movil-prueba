package pe.edu.upeu.pharmamobil

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import pe.edu.upeu.pharmamobil.domain.model.Producto
import pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos
import pe.edu.upeu.pharmamobil.domain.usecase.ProductoFlowUseCase
import pe.edu.upeu.pharmamobil.domain.usecase.ProductoUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Reto02Test {

    @Test
    fun probarCorrutinasYFlow() = runTest {
        val useCase = ProductoUseCase()
        val productos = useCase.obtenerProductos()
        assertEquals(3, productos.size)
        assertEquals("Paracetamol", productos[0].nombre)
        assertEquals(8.50, productos[0].precio)
        assertEquals(100, productos[0].stock)

        val flowUseCase = ProductoFlowUseCase()

        val estados = mutableListOf<ResultadoProductos>()
        flowUseCase.cargarProductosEstado().collectLatest { estados.add(it) }
        assertEquals(2, estados.size)
        assertTrue(estados[0] is ResultadoProductos.Cargando)
        assertTrue(estados[1] is ResultadoProductos.Exito)

        val listas = mutableListOf<List<Producto>>()
        flowUseCase.observarProductos().collectLatest { listas.add(it) }
        assertEquals(2, listas.size)
        assertTrue(listas[0].isEmpty())
        assertEquals(90, listas[1].first { it.id == 1L }.stock)
    }
}
