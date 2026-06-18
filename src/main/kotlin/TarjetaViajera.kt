import java.time.LocalDate

class TarjetaViajera(
    var saldo: Double = 0.0,
    val fechaAdquisicion: LocalDate = LocalDate.now(),
    var deuda: Double = 0.0
){
    fun acreditar(monto: Double){saldo += monto}
    fun debitar(monto: Double){saldo -= monto}
    fun sumarDeuda(monto: Double){deuda += monto}
    fun esSaldoSuficiente(monto: Double) = saldo >= monto
    fun esDeudaMenorA(monto: Double) = deuda < monto
    fun fueAdquiridaHaceMeses(cantidadMeses: Long) = fechaAdquisicion.isBefore(LocalDate.now().minusMonths(cantidadMeses))
}