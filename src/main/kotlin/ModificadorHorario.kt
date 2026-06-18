import java.time.LocalTime

object ModificadorHorario: ModificadorCosto {
    override fun aplicarModificador(monto: Double, cliente: Cliente, horaViaje: LocalTime): Double =
        if (horaViaje.enHoraPico()) monto + 2.0 else monto
}