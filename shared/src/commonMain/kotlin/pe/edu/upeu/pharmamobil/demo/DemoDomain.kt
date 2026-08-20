package pe.edu.upeu.pharmamobil.demo

import pe.edu.upeu.pharmamobil.domain.model.*
import pe.edu.upeu.pharmamobil.domain.result.ResultadoProductos

fun demostrarDominio() {
    val cliente = Cliente(1L, "Juan Pérez", "juan@gmail.com")
    println("Teléfono: ${cliente.obtenerTelefonoFormateado()}")

    val prod = Producto(1L, "Paracetamol", 2.50, 10)
    val prodActualizado = prod.copy(stock = 8)

    val detalle = DetallePedido(prodActualizado, 2)
    val pedido = Pedido(100L, cliente, listOf(detalle), EstadoPedido.Pendiente)

    println("Total del pedido: S/ ${pedido.calcularTotal()}")
}

fun mostrarResultado(resultado: ResultadoProductos) {
    when (resultado) {
        ResultadoProductos.Cargando -> println("Cargando productos...")
        is ResultadoProductos.Exito -> println("Productos encontrados: ${resultado.productos.size}")
        is ResultadoProductos.Error -> println("Error: ${resultado.mensaje}")
    }
}
