class ObserverDistancia: ObserverViaje {
    override fun observar(viaje: Viaje) {
        val km = viaje.calcularDistancia()
        val mes = viaje.mesDelViaje()
        viaje.clientes().forEach { cliente -> cliente.registrarKm(km, mes) }
    }
}