package com.example.primerproyecto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.primerproyecto.databinding.ActivityImcBinding


class ActivityImc : AppCompatActivity() {

    lateinit var binding: ActivityImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcularImc.setOnClickListener {
            cambiarTexto(calcularImc(obtenerPeso(), obtenerEstatura()))
        }

        binding.Regresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    fun calcularImc(peso: Double?, estatura: Double?): Double? {
        val imc: Double?

        if ( peso != null && estatura != null) {
            val altura = estatura / 100
            imc = peso / (altura * altura)
        } else imc = null
        return imc
    }

    fun obtenerPeso(): Double? {
        val peso : Double? = binding.peso.text?.toString()?.toDoubleOrNull()
        return peso
    }

    fun obtenerEstatura(): Double? {
        val estatura : Double? = binding.estatura.text?.toString()?.toDoubleOrNull()
        return estatura
    }

    fun cambiarTexto(imc : Double?) {
        val mensaje = when {
            imc == null -> "Datos no validos"
            else -> {
                val imcFormateado = String.format("%.2f", imc)
                when {
                    imc > 30 -> "$imcFormateado Obesidad"
                    imc > 25 -> "$imcFormateado Sobrepeso"
                    imc > 18.5 -> "$imcFormateado Saludable"
                    else -> "$imcFormateado Bajo peso"
                }
            }
        }
        binding.ResultadoImc.text = mensaje
    }
}
