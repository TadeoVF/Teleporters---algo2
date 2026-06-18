import java.time.LocalTime

interface ModificadorCosto {
    fun aplicarModificador(monto: Double, cliente: Cliente, horaViaje: LocalTime): Double
}