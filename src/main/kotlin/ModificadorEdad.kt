import java.time.LocalTime

object ModificadorEdad : ModificadorCosto {
    override fun aplicarModificador(monto: Double, cliente: Cliente, horaViaje: LocalTime): Double =
        when {
            cliente.esJoven() -> monto * 0.7
            cliente.esViejo() -> monto * 0.5
            else -> monto
        }
}
