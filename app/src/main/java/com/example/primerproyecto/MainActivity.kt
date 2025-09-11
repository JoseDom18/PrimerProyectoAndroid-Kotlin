package com.example.primerproyecto

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val miNumero: EditText=findViewById(R.id.introducirEdad) // recibe la entrada de texto
        val miBoton: Button=findViewById(R.id.button) // boton de accion
        val textoResultado: TextView=findViewById(R.id.mensaje) // muestra el resultado
        val botonSumar: Button=findViewById(R.id.sumar) // boton de accion
        val botonRestar: Button=findViewById(R.id.resta) // boton de accion
        val miNombre: EditText=findViewById(R.id.nombre) // recibe nombre
        val botoReinicio: Button=findViewById(R.id.reiniciar) // boton de reinicio

        miBoton.setOnClickListener {

            val numeroResultado = miNumero.text.toString().toIntOrNull()
            val nombre: String?= miNombre.text?.toString()

//            if (numeroResultado == null) textoResultado.text="Introduce un número."
//            else if (numeroResultado < 18) {
//                textoResultado.text="Eres menor de edad."
//            } else if (numeroResultado > 18) {
//                textoResultado.text="Eres mayor de edad."
//            } else {
//                textoResultado.text="Tienes 18 años."
//            }

            actulizarMensaje(numeroResultado, nombre, textoResultado)

        }

        botonSumar.setOnClickListener {

            var numeroResultado = miNumero.text.toString().toIntOrNull()
            val nombre: String?= miNombre.text?.toString()

            if(numeroResultado==null) textoResultado.text="Introduce un número."
            else{
                miNumero.setText((++numeroResultado).toString())
                actulizarMensaje(numeroResultado, nombre, textoResultado)

            }

        }

        botonRestar.setOnClickListener {

            var numeroResultado = miNumero.text.toString().toIntOrNull()
            val nombre: String?= miNombre.text?.toString()

            if(numeroResultado==null) textoResultado.text="Introduce un número."
            else {
                miNumero.setText((--numeroResultado).toString())
                actulizarMensaje(numeroResultado, nombre, textoResultado)
            }

        }

        botoReinicio.setOnClickListener {

            textoResultado.text=""
            miNombre.setText("")
            miNumero.setText("")

        }

    }

    private fun actulizarMensaje (edad:Int?, nombre: String?, textoResultado: TextView) {
        val mensaje = when {
            nombre.isNullOrBlank() -> "Introduce tu nombre."
            edad==null -> "Introduce tu edad."
            edad < 18 -> "$nombre, eres menor de edad."
            edad > 18 -> "$nombre, eres mayor de edad."
            else -> "$nombre, tienes justo 18 años."
        }
        textoResultado.text= mensaje
    }
}