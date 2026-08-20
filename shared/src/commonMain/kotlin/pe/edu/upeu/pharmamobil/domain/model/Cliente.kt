package pe.edu.upeu.pharmamobil.domain.model

data class Cliente(
    val id: Long,
    val nombres: String,
    val correo: String,
    val telefono: String? = null
) {
    fun obtenerTelefonoFormateado(): String {
        return telefono ?: "No registrado"
    }
}
