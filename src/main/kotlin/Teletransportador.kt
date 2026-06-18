import java.time.LocalDateTime
import java.time.LocalTime

class Teletransportador(val costoBase: Double = 5.0) {
    private val modificadoresCosto: MutableList<ModificadorCosto> = mutableListOf()

    fun agregarModificador(modificador: ModificadorCosto){modificadoresCosto.add(modificador)}
    fun eliminarModificador(modificador: ModificadorCosto){modificadoresCosto.remove(modificador)}

    fun costoFinal(cliente: Cliente, horaViaje: LocalTime): Double =
        modificadoresCosto.fold(costoBase) { costoAcumulado, modificador ->
            modificador.aplicarModificador( costoAcumulado, cliente, horaViaje)
        }
}