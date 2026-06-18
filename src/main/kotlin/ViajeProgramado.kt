import java.time.LocalDateTime

class ViajeProgramado(
    origen: Punto,
    destino: Punto,
    tiempoSalida: LocalDateTime,
    teletransportador: Teletransportador,
    calculoDistanciaPuntos: CalculoDistanciaPuntos,
    var maxClientes: Int,
    var maxDeuda: Double
): Viaje(origen, destino, tiempoSalida, teletransportador, calculoDistanciaPuntos) {

    val deudaMaximaPlus: Double = 120.0

    fun esAntesHorarioIngreso(): Boolean = LocalDateTime.now().isBefore(tiempoSalida.minusHours(2))
    fun esPasadoHorarioIngreso(): Boolean = LocalDateTime.now().isAfter(tiempoSalida)
    fun esViajeLleno(): Boolean = clientes().size >= maxClientes
    fun esMaxDeuda(cliente: Cliente): Boolean = !cliente.esDeudaMenorA(maxDeuda)
    fun esDeudaSuperior(cliente: Cliente): Boolean = !cliente.esDeudaMenorA(deudaMaximaPlus)

    fun validarHorarioPrevio(){ if(this.esAntesHorarioIngreso()) throw ExcepcionesPropias("Es temprano para ingresar") }
    fun validarMuyTarde(){ if(this.esPasadoHorarioIngreso()) throw ExcepcionesPropias("El ingreso termino") }
    fun validarLugares(){ if(this.esViajeLleno()) throw ExcepcionesPropias("el viaje esta lleno") }
    fun validarDeudaMaxima(cliente: Cliente){ if(this.esMaxDeuda(cliente)) throw ExcepcionesPropias("te pasaste del limite de deuda del viaje") }
    fun validarDeudaMaximaPlus(cliente: Cliente){ if(this.esDeudaSuperior(cliente)) throw ExcepcionesPropias("te pasaste del limite de deuda general") }

    override fun validarIngreso(cliente: Cliente){
        this.validarHorarioPrevio()
        this.validarMuyTarde()
        this.validarLugares()
        this.validarDeudaMaxima(cliente)
        this.validarDeudaMaximaPlus(cliente)
    }
    override fun cobrar(cliente: Cliente, precio: Double){
        if(cliente.saldo() >= precio){
            cliente.debitar(precio)
        } else{
            val restante = precio - cliente.saldo()
            if(cliente.saldo()>0){
                cliente.debitar(cliente.saldo())
            }
            cliente.sumarDeuda(restante)
        }
    }
    override fun calcularDistancia() = calculoDistanciaPuntos.calcularDistancia(origen, destino)

}