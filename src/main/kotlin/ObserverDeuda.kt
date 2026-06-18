class ObserverDeuda(
    private val mailSender: MailSender,
    private val maxDeuda: Double
): ObserverViaje {
    override fun observar(viaje: Viaje) {
        viaje.clientes()
            .filter{!it.esDeudaMenorA(maxDeuda)}
            .forEach { cliente -> mailSender.enviar(Mail(
                emisor = "mailteleporters@gmail.com",
                receptor = cliente.dirMail,
                asunto = "guarda con la deuda",
                cuerpo = "tenes demaciada deuda"
            )) }
    }
}