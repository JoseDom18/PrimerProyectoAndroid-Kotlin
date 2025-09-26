package com.example.primerproyecto

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primerproyecto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState != null) {
            val mensaje = savedInstanceState.getString("mensaje")

            binding.mensaje.setText(mensaje)
        }

        binding.button.setOnClickListener {

            actulizarMensaje(obtenerEdad(), obtenerNombre())

        }

        binding.sumar.setOnClickListener {

            if(obtenerEdad()==null) binding.mensaje.text="Introduce un número"
            else{
                binding.introducirEdad.setText((obtenerEdad()?.plus(1)).toString())
                actulizarMensaje(obtenerEdad(), obtenerNombre())

            }

        }

        binding.resta.setOnClickListener {

            if(obtenerEdad()==null) binding.mensaje.text="Introduce un número"
            else{
                binding.introducirEdad.setText((obtenerEdad()?.minus(1)).toString())
                actulizarMensaje(obtenerEdad(), obtenerNombre())

            }

        }

        binding.reiniciar.setOnClickListener {

            binding.introducirEdad.setText("")
            binding.nombre.setText("")
            binding.mensaje.text=""

        }

        binding.imc.setOnClickListener {
            val intent = Intent(this, ActivityImc::class.java)
            startActivity(intent)
        }


    }


    override fun onSaveInstanceState(objeto: Bundle){

        objeto.putString("mensaje", actulizarMensaje(obtenerEdad(), obtenerNombre()) ?: "no se guardo")
        super.onSaveInstanceState(objeto)

    }

    private fun actulizarMensaje (edad:Int?, nombre: String?): String? {
        val mensaje = when {
            nombre.isNullOrBlank() -> getString(R.string.res1)
            edad==null -> getString(R.string.res2)
            edad < 18 -> getString(R.string.res3, nombre)
            edad > 18 -> getString(R.string.res4, nombre)
            else -> getString(R.string.res5, nombre)
        }
        binding.mensaje.text= mensaje
        return mensaje
    }

    private fun obtenerEdad(): Int? {

        val numeroResultado = binding.introducirEdad.text.toString().toIntOrNull()
        return numeroResultado
    }

    private fun obtenerNombre(): String? {
        val nombre : String? = binding.nombre.text?.toString()
        return nombre
    }
}