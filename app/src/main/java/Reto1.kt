import kotlin.random.Random

fun main() {
    println("¡Bienvenido a Craps!")
    println("Instrucciones:")
    println("1. Se lanzarán dos dados.")
    println("2. Ganarás si obtienes:")
    println(" - Un par de unos.")
    println(" - Un total de tres.")
    println(" - Un total de once.")
    println(" - Un total de dos o doce.")
    println(" - Un total de siete.")
    println("3. En cualquier otro caso, perderás.")

    while (true) {
        println("¿Cuánto dinero quieres apostar? (Ingresa 0 para salir)")
        val apuesta = readLine()?.toIntOrNull() ?: continue

        if (apuesta <= 0) {
            println("Gracias por jugar. ¡Hasta luego!")
            break
        }

        val resultadoDados = lanzarDados()
        println("Resultado de los dados: ${resultadoDados[0]} y ${resultadoDados[1]}")

        val totalDados = resultadoDados.sum()
        val resultado = when (totalDados) {
            2, 12 -> "Perdiste" // Dos o doce
            3, 11 -> "Ganaste" // Tres o once
            7 -> "Ganaste" // Siete
            1 -> "Ganaste" // Par de unos
            else -> "Perdiste"
        }

        println("Total de los dados: $totalDados")
        println(resultado)
    }
}

fun lanzarDados(): List<Int> {
    val dado1 = Random.nextInt(1, 7)
    val dado2 = Random.nextInt(1, 7)
    return listOf(dado1, dado2)
}