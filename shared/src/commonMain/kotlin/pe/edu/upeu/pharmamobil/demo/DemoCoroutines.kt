package pe.edu.upeu.pharmamobil.demo

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos
import pe.edu.upeu.pharmamobil.domain.usecase.ProductoFlowUseCase
import pe.edu.upeu.pharmamobil.domain.usecase.ProductoUseCase

fun main() = runBlocking {
    println("=== 1. PRUEBA SUSPEND ===")
    val useCase = ProductoUseCase()
    val productos = useCase.obtenerProductos()
    println("Productos obtenidos: ${productos.size}")

    println("\n=== 2. PRUEBA FLOW LISTA ===")
    val flowUseCase = ProductoFlowUseCase()
    flowUseCase.observarProductos().collectLatest { lista ->
        println("Emisión lista recibida (tamaño): ${lista.size}")
    }

    println("\n=== 3. PRUEBA FLOW ESTADOS ===")
    flowUseCase.cargarProductosEstado().collectLatest { estado ->
        when (estado) {
            ResultadoProductos.Cargando -> println("Estado: Cargando...")
            is ResultadoProductos.Exito -> println("Estado: Éxito con ${estado.productos.size} productos")
            is ResultadoProductos.Error -> println("Estado: Error - ${estado.mensaje}")
        }
    }
}