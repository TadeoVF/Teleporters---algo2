class AdaptadorDistancia(private val servicioExterno: ServicioExterno) : CalculoDistanciaPuntos {
    override fun calcularDistancia(origen: Punto, destino: Punto): Double {
        val (parteEntera, parteDecimal) = servicioExterno.calcularDistancia(
            origen.x, origen.y,
            destino.x, destino.y,
            18
        )
        val millas = parteEntera + parteDecimal / 100.0
        return millas * 1.609344
    }
}
