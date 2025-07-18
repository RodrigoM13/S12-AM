package com.example.rodrigo_s12

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rodrigo_s12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculadoraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.pantalla.observe(this) {
            binding.txtPantalla.text = it
        }

        val botonesNumeros = mapOf(
            binding.btn0 to "0",
            binding.btn1 to "1",
            binding.btn2 to "2",
            binding.btn3 to "3",
            binding.btn4 to "4",
            binding.btn5 to "5",
            binding.btn6 to "6",
            binding.btn7 to "7",
            binding.btn8 to "8",
            binding.btn9 to "9"
        )

        botonesNumeros.forEach { (boton, valor) ->
            boton.setOnClickListener { viewModel.ingresarNumero(valor) }
        }

        binding.btnSumar.setOnClickListener { viewModel.seleccionarOperacion("+") }
        binding.btnRestar.setOnClickListener { viewModel.seleccionarOperacion("-") }
        binding.btnMultiplicar.setOnClickListener { viewModel.seleccionarOperacion("×") }
        binding.btnDividir.setOnClickListener { viewModel.seleccionarOperacion("/") }
        binding.btnPunto.setOnClickListener { viewModel.ingresarPunto() }
        binding.btnIgual.setOnClickListener { viewModel.calcularResultado() }
        binding.btnClear.setOnClickListener { viewModel.limpiar() }
    }
}

