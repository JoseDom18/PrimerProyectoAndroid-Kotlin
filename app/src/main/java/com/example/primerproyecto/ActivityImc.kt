package com.example.primerproyecto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class ActivityImc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // **ESTO ES LO CLAVE:** Vincula el archivo XML (activityImc.xml) a esta clase
        setContentView(R.layout.activity_imc)
    }
}
