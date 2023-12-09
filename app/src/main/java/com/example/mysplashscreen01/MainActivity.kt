package com.example.mysplashscreen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread.sleep(3000) //demora de 3seg
        installSplashScreen().setKeepOnScreenCondition{false}

        //quiero asignar al botón la función de salir o terminar la app
        //declaro una variable para mi activity:
        val activity = MainActivity()

        //declaro e inicializo a la variable closeApplicationBtn
        val closeApplicationBtn: Button = findViewById(R.id.button)

        //añadiendo el listener a nuestro button:
        closeApplicationBtn.setOnClickListener {
            // finalizando la actividad
            activity.finish()

            // saliendo de la actividad
            System.exit(0)
        }


    }
}