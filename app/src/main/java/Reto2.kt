fun main() {
    // Generamos un número aleatorio entre 1 y 4 para simular el color de la bolita obtenida
    val colorBolita = (1..4).random()

    // Precio de la compra del cliente
    val precioCompra = 60000.0 // Puedes cambiar este valor por el valor real de la compra

    // Inicializamos las variables de descuento y total a pagar
    var descuento = 0.0
    var totalAPagar = 0.0

    if (precioCompra > 50000) {
        when (colorBolita) {
            1 -> { // Bolita roja (10% de descuento)
                descuento = precioCompra * 0.1
            }
            2 -> { // Bolita azul (30% de descuento)
                descuento = precioCompra * 0.3
            }
            3 -> { // Bolita amarilla (50% de descuento)
                descuento = precioCompra * 0.5
            }
            4 -> { // Bolita blanca (Compra gratis)
                descuento = precioCompra
            }
        }

        totalAPagar = precioCompra - descuento

        println("¡Felicitaciones! Obtuviste una bolita de color ${obtenerColorBolita(colorBolita)}.")
        println("Tu descuento es de ${descuento} y el total a pagar es ${totalAPagar}.")
    } else {
        println("Lo sentimos, para obtener el descuento debes realizar una compra mayor a 50.000.")
    }
}

fun obtenerColorBolita(color: Int): String {
    return when (color) {
        1 -> "roja"
        2 -> "azul"
        3 -> "amarilla"
        4 -> "blanca"
        else -> "desconocido"
    }
}