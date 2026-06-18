class ObserverCobrar: ObserverViaje {
    override fun observar(viaje: Viaje){
        viaje.clientes().forEach { cliente -> viaje.cobrar(cliente, viaje.precioPara(cliente)) }
    }
}