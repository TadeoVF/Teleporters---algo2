import java.time.LocalDateTime

class Itinerario(
    origen: Punto,
    destino: Punto,
    tiempoSalida: LocalDateTime,
    teletransportador: Teletransportador,
    calculoDistanciaPuntos: CalculoDistanciaPuntos,
): Viaje(origen, destino, tiempoSalida, teletransportador, calculoDistanciaPuntos) {

    val antiguedadMinTarjeta: Long = 6

    private val subrrecorridos: MutableList<Viaje> = mutableListOf()
    fun agregarsubrrecorrido(subrrecorrido: Viaje){subrrecorridos.add(subrrecorrido)}
    fun quitarsubrrecorrido(subrrecorrido: Viaje)  { subrrecorridos.remove(subrrecorrido) }

    fun esPocoDinero(cliente: Cliente): Boolean = !cliente.esSaldoSuficiente(precioPara(cliente))
    fun esTarjetaValida(cliente: Cliente): Boolean = cliente.fueAdquiridaHaceMeses(antiguedadMinTarjeta)

    fun validarDinero(cliente: Cliente){ if(this.esPocoDinero(cliente)) throw ExcepcionesPropias("no le alcanza el dinero") }
    fun validarTarjeta(cliente: Cliente){ if(!this.esTarjetaValida(cliente)) throw ExcepcionesPropias("la tarjeta es muy nueva")}

    override fun validarIngreso(cliente: Cliente){
        this.validarDinero(cliente)
        this.validarTarjeta(cliente)
    }


    override fun cobrar(cliente: Cliente, precio: Double) = cliente.debitar(precio)
    override fun calcularDistancia() = subrrecorridos.sumOf {it.calcularDistancia()}
}