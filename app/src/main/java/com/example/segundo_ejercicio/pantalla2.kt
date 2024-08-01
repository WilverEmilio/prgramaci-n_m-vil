package com.example.segundo_ejercicio

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pantalla2 : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("nombre", "No disponible")
        val lastname = sharedPreferences.getString("apellido", "No disponible")
        val phone = sharedPreferences.getString("telefono", "No disponible")
        val age = sharedPreferences.getString("edad", "No disponible")
        val email = sharedPreferences.getString("mail", "No disponible")

        val nameTextView: TextView = findViewById(R.id.textView8)
        val lastnameTextView: TextView = findViewById(R.id.textView9)
        val phoneTextView: TextView = findViewById(R.id.textView10)
        val ageTextView: TextView = findViewById(R.id.textView11)
        val emailTextView: TextView = findViewById(R.id.textView12)

        nameTextView.text = "Nombre: $name"
        lastnameTextView.text = "Apellido: $lastname"
        phoneTextView.text = "Teléfono: $phone"
        ageTextView.text = "Edad: $age"
        emailTextView.text = "Email: $email"



    }
}