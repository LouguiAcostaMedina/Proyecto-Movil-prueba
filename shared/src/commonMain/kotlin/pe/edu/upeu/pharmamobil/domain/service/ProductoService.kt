package pe.edu.upeu.pharmamobil.domain.service

import pe.edu.upeu.pharmamobil.domain.model.Producto

class ProductoService {
    fun filtrarDisponibles(productos: List<Producto>): List<Producto> {
        return productos.filter { it.stock > 0 }
    }

    fun obtenerNombres(productos: List<Producto>): List<String> {
        return productos.map { it.nombre }
    }

    fun buscarPorId(productos: List<Producto>, id: Long): Producto? {
        return productos.find { it.id == id }
    }
}
