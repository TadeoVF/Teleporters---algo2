import java.time.LocalDateTime
import java.time.YearMonth

abstract class Viaje(
    val origen: Punto,
    val destino: Punto,
    val tiempoSalida: LocalDateTime,
    val teletransportador: Teletransportador,
    val calculoDistanciaPuntos: CalculoDistanciaPuntos
){
    private val clientes: MutableList<Cliente> = mutableListOf()
    private val observadores: MutableList<ObserverViaje> = mutableListOf()

    fun agregarObserver(observer: ObserverViaje) {observadores.add(observer)}
    fun eliminarObserver(observer: ObserverViaje) {observadores.remove(observer)}
    fun completarViaje() = observadores.forEach {it.observar(this)}

    fun agregarCliente(cliente: Cliente) {
        validarIngreso(cliente)
        clientes.add(cliente)
    }
    fun eliminarCliente(cliente: Cliente) {clientes.remove(cliente)}
    fun clientes() = clientes.toList()
    fun precioPara(cliente: Cliente) = teletransportador.costoFinal(cliente, tiempoSalida.toLocalTime())
    fun mesDelViaje() = YearMonth.from(tiempoSalida)

    abstract fun cobrar(cliente: Cliente, precio: Double)
    abstract fun validarIngreso(cliente: Cliente)
    abstract fun calcularDistancia(): Double
}