package com.example.primerproyecto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
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
            cambiarImagen(binding.imagen, calcularImc(obtenerPeso(),obtenerEstatura()))
        }

        binding.Regresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onPause() {
        super.onPause()
        val datos = getSharedPreferences("datosImc", Context.MODE_PRIVATE)
        with(datos.edit()){
            putString("resultadoImc", cambiarTexto(calcularImc(obtenerPeso(), obtenerEstatura())))
            apply()
        }
    }

    override fun onResume() {
        super.onResume()
        val datos = getSharedPreferences("datosImc", Context.MODE_PRIVATE)
        val mensaje = datos.getString("resultadoImc", "")

        binding.ResultadoImc.setText(mensaje)

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

    fun cambiarTexto(imc : Double?): String? {
        val mensaje = when {
            imc == null -> getString(R.string.error)
            else -> {
                when {
                    imc > 30 -> getString(R.string.imc1, imc)
                    imc > 25 -> getString(R.string.imc2, imc)
                    imc > 18.5 -> getString(R.string.imc3, imc)
                    else -> getString(R.string.imc4, imc)
                }
            }
        }
        binding.ResultadoImc.text = mensaje
        return mensaje
    }
    fun cambiarImagen(imageView: ImageView, imc: Double?) {
        when {
            imc == null -> imageView.setImageResource(R.drawable.error)
            else -> {
                when {
                    imc > 30 -> imageView.setImageResource(R.drawable.obesidad)
                    imc > 25 -> imageView.setImageResource(R.drawable.sobrepeso)
                    imc > 18.5 -> imageView.setImageResource(R.drawable.normal)
                    else -> imageView.setImageResource(R.drawable.bajo)
                }
            }
        }
    }
}
