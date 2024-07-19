package com.example.segundo_ejercicio

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn: Button = findViewById(R.id.button2)
        btn.setOnClickListener{

            val name: EditText = findViewById(R.id.nombre)
            val lastname: EditText = findViewById(R.id.apellido)
            val phone: EditText = findViewById(R.id.telefono)
            val age: EditText = findViewById(R.id.edad)
            val email: EditText = findViewById(R.id.email)

            if(name.text.isNotEmpty() && lastname.text.isNotEmpty() && phone.text.isNotEmpty() && age.text.isNotEmpty() && email.text.isNotEmpty()){
                val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("nombre", name.text.toString())
                    putString("apellido", lastname.text.toString())
                    putString("telefono", phone.text.toString())
                    putString("edad", age.text.toString())
                    putString("mail", email.text.toString())
                    apply()
                }
                val intent: Intent = Intent(this,pantalla2::class.java)
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}