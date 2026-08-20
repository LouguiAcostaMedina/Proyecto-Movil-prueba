package pe.edu.pe

import kotlinx.coroutines.runBlocking
import pe.edu.upeu.pharmamobil.demo.demostrarDominio
import pe.edu.upeu.pharmamobil.domain.usecase.ProductoFlowUseCase
import pe.edu.upeu.pharmamobil.domain.usecase.ProductoUseCase
import kotlin.test.Test

class DemoTest {
    @Test
    fun probarSesion2() = runBlocking {
        println("=== 1. DOMINIO Y NULL-SAFETY ===")
        demostrarDominio()

        println("\n=== 2. SUSPEND FUNCTION ===")
        val useCase = ProductoUseCase()
        val productos = useCase.obtenerProductos()
        productos.forEach { println("  ${it.nombre} - S/ ${it.precio}") }

        println("\n=== 3. FLOW DE LISTAS ===")
        val flowUseCase = ProductoFlowUseCase()
        flowUseCase.observarProductos().collect { lista ->
            if (lista.isEmpty()) println("  Cargando...") else lista.forEach { println("  ${it.nombre} - stock: ${it.stock}") }
        }

        println("\n=== 4. FLOW DE ESTADOS ===")
        flowUseCase.cargarProductosEstado().collect { resultado ->
            when (resultado) {
                is pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos.Cargando -> println("  Cargando...")
                is pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos.Exito -> resultado.productos.forEach { println("  ${it.nombre}") }
                is pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos.Error -> println("  Error: ${resultado.mensaje}")
            }
        }
    }
}
