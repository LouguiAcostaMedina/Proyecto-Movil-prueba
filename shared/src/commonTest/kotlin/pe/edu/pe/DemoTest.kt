package pe.edu.pe

import kotlinx.coroutines.runBlocking
import pe.edu.upeu.pharmamobil.demo.demostrarDominio
import pe.edu.upeu.pharmamobil.demo.mostrarResultado
import pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos
import pe.edu.upeu.pharmamobil.domain.usecase.ProductoFlowUseCase
import kotlin.test.Test

class DemoTest {
    @Test
    fun probarSesion2() = runBlocking {
        println("=== 1. DOMINIO Y NULL-SAFETY ===")
        demostrarDominio()

        println("\n=== 2. SEALED CLASS Y WHEN ===")
        mostrarResultado(ResultadoProductos.Cargando)

        println("\n=== 3. CORRUTINAS Y FLOW ===")
        val flowUseCase = ProductoFlowUseCase()
        flowUseCase.observarProductos().collect { resultado ->
            mostrarResultado(resultado)
        }
    }
}