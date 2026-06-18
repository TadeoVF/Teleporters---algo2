import java.time.LocalTime

fun LocalTime.enHoraPico() = hour in 7..10 || hour in 16..19

class ExcepcionesPropias(mensaje: String) : RuntimeException(mensaje)