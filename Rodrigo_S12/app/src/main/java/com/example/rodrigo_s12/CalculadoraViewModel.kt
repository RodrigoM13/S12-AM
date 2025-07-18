package com.example.rodrigo_s12

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculadoraViewModel : ViewModel() {

    private val _pantalla = MutableLiveData("0")
    val pantalla: LiveData<String> get() = _pantalla

    private var operando1: Double? = null
    private var operacion: String? = null
    private var nuevoNumero = true

    fun ingresarNumero(num: String) {
        if (nuevoNumero) {
            _pantalla.value = num
            nuevoNumero = false
        } else {
            _pantalla.value += num
        }
    }

    fun ingresarPunto() {
        if (nuevoNumero) {
            _pantalla.value = "0."
            nuevoNumero = false
        } else if (!_pantalla.value.orEmpty().contains(".")) {
            _pantalla.value += "."
        }
    }

    fun seleccionarOperacion(op: String) {
        operando1 = _pantalla.value?.toDoubleOrNull()
        operacion = op
        nuevoNumero = true
    }

    fun calcularResultado() {
        val operando2 = _pantalla.value?.toDoubleOrNull()
        if (operando1 != null && operando2 != null && operacion != null) {
            val resultado = when (operacion) {
                "+" -> operando1!! + operando2
                "-" -> operando1!! - operando2
                "×" -> operando1!! * operando2
                "/" -> if (operando2 != 0.0) operando1!! / operando2 else null
                else -> null
            }
            _pantalla.value = resultado?.toString() ?: "Error"
            operando1 = null
            operacion = null
            nuevoNumero = true
        }
    }

    fun limpiar() {
        _pantalla.value = "0"
        operando1 = null
        operacion = null
        nuevoNumero = true
    }
}
