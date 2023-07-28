import java.util.Scanner

class Nequi(private val phoneNumber: String, private var password: String) {
    private var saldoDisponible = 4500

    fun acceder() {
        var intentosRestantes = 3

        while (intentosRestantes > 0) {
            val scanner = Scanner(System.`in`)
            println("Ingrese su número de celular:")
            val inputPhoneNumber = scanner.nextLine()

            println("Ingrese su clave de 4 dígitos:")
            val inputPassword = scanner.nextLine()

            if (inputPhoneNumber == phoneNumber && inputPassword == password) {
                println("¡Bienvenido(a) al Nequi!")
                mostrarSaldo()
                return
            } else {
                intentosRestantes--
                if (intentosRestantes > 0) {
                    println("¡Upps! Parece que tus datos de acceso no son correctos. Tienes $intentosRestantes intento(s) más.")
                } else {
                    println("¡Upps! Parece que tus datos de acceso no son correctos. Ya no tienes más intentos.")
                    return
                }
            }
        }
    }

    private fun mostrarSaldo() {
        println("Saldo disponible: $$saldoDisponible")
    }

    private fun validarSaldoSuficiente(valor: Int): Boolean {
        return saldoDisponible >= valor
    }

    fun sacar() {
        val scanner = Scanner(System.`in`)
        println("¿Desea retirar dinero del cajero o del punto físico?")
        println("1. Cajero")
        println("2. Punto físico")

        val opcion = scanner.nextInt()

        if (opcion == 1 || opcion == 2) {
            val lugarRetiro = if (opcion == 1) "cajero" else "punto físico"
            println("Ingrese el valor a retirar:")
            val valorRetiro = scanner.nextInt()

            if (valorRetiro <= 0) {
                println("El valor a retirar debe ser mayor a cero.")
                return
            }

            if (!validarSaldoSuficiente(valorRetiro)) {
                println("No te alcanza para retirar $$valorRetiro en el $lugarRetiro.")
                return
            }

            println("¿Está seguro que desea retirar $$valorRetiro del $lugarRetiro? (si/no)")
            val confirmacion = scanner.next()

            if (confirmacion.equals("si", ignoreCase = true)) {
                saldoDisponible -= valorRetiro
                println("Retiro exitoso. Código de 6 dígitos: ${generarCodigoRetiro()}")
                mostrarSaldo()
            } else {
                println("Retiro cancelado.")
            }
        } else {
            println("Opción no válida.")
        }
    }

    fun enviar() {
        val scanner = Scanner(System.`in`)
        println("Ingrese el número de teléfono al que desea enviar dinero:")
        val numeroDestino = scanner.nextLine()

        println("Ingrese el valor a enviar:")
        val valorEnviar = scanner.nextInt()

        if (valorEnviar <= 0) {
            println("El valor a enviar debe ser mayor a cero.")
            return
        }

        if (!validarSaldoSuficiente(valorEnviar)) {
            println("No es posible enviar $$valorEnviar. Saldo insuficiente.")
            return
        }

        saldoDisponible -= valorEnviar
        println("Envío exitoso a $numeroDestino. Saldo disponible: $$saldoDisponible")
    }

    fun recarga() {
        val scanner = Scanner(System.`in`)
        println("Ingrese el valor a recargar:")
        val valorRecarga = scanner.nextInt()

        if (valorRecarga <= 0) {
            println("El valor a recargar debe ser mayor a cero.")
            return
        }

        println("¿Está seguro que desea recargar $$valorRecarga? (si/no)")
        val confirmacion = scanner.next()

        if (confirmacion.equals("si", ignoreCase = true)) {
            saldoDisponible += valorRecarga
            println("Recarga exitosa. Saldo disponible: $$saldoDisponible")
        } else {
            println("Recarga cancelada.")
        }
    }

    private fun generarCodigoRetiro(): Int {
        return (100000..999999).random()
    }

    fun cambiarContrasena() {
        val scanner = Scanner(System.`in`)
        println("Ingrese la contraseña actual:")
        val inputPassword = scanner.next()

        if (inputPassword == password) {
            println("Contraseña actual correcta. Ingresa tu nueva contraseña:")
            val newPassword = scanner.next()
            password = newPassword
            println("Contraseña cambiada exitosamente.")
        } else {
            println("Contraseña actual incorrecta. No se pudo cambiar la contraseña.")
        }
    }

    fun desactivarCuenta() {
        val scanner = Scanner(System.`in`)
        println("¿Estás seguro que deseas desactivar tu cuenta? (si/no)")
        val confirmacion = scanner.next()

        if (confirmacion.equals("si", ignoreCase = true)) {
            println("Cuenta desactivada exitosamente. Gracias por usar Nequi. ¡Hasta pronto!")
            System.exit(0) // Sale del programa
        } else {
            println("Desactivación cancelada.")
        }
    }
}

fun main() {
    val nequi = Nequi("3008491294", "1234")
    nequi.acceder()

    var continuar = true
    val scanner = Scanner(System.`in`)

    while (continuar) {
        println("¿Qué acción desea realizar?")
        println("1. Sacar")
        println("2. Enviar")
        println("3. Recarga")
        println("4. Cambiar contraseña")
        println("5. Desactivar tu cuenta")
        println("6. Salir")

        val opcion = scanner.nextInt()

        when (opcion) {
            1 -> nequi.sacar()
            2 -> nequi.enviar()
            3 -> nequi.recarga()
            4 -> nequi.cambiarContrasena()
            5 -> nequi.desactivarCuenta()
            6 -> {
                continuar = false
                println("Gracias por usar Nequi. ¡Hasta pronto!")
            }
            else -> println("Opción no válida.")
        }
    }
}
