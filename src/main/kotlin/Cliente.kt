import java.time.YearMonth

class Cliente(
    val nombre: String,
    val edad: Int,
    val tarjeta: TarjetaViajera,
    val dirMail: String = "maracuya@gmail.com"
) {
    fun esJoven(): Boolean = edad < 18
    fun esViejo(): Boolean = edad > 65

    fun esSaldoSuficiente(monto: Double) = tarjeta.esSaldoSuficiente(monto)
    fun esDeudaMenorA(limite: Double) = tarjeta.esDeudaMenorA(limite)
    fun fueAdquiridaHaceMeses(cantidadMeses: Long) = tarjeta.fueAdquiridaHaceMeses(cantidadMeses)
    fun acreditar(monto: Double) = tarjeta.acreditar(monto)
    fun debitar(monto: Double) = tarjeta.debitar(monto)
    fun sumarDeuda(monto: Double) = tarjeta.sumarDeuda(monto)
    fun deuda() = tarjeta.deuda
    fun saldo() = tarjeta.saldo

    private val kmPorMes: MutableMap<YearMonth, Double> = mutableMapOf()
    fun registrarKm(km: Double, mes: YearMonth) {
        kmPorMes[mes] = (kmPorMes[mes] ?: 0.0) + km
    }
    fun kmRecorridosEn(mes: YearMonth) = kmPorMes[mes] ?: 0.0

}
