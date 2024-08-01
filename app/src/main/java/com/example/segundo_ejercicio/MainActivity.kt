package com.example.segundo_ejercicio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NAME_SHADOWING")
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

        val refName: EditText = findViewById(R.id.nombre)
        val refLastName: EditText = findViewById(R.id.apellido)
        val refAge: EditText = findViewById(R.id.edad)
        val refEmail: EditText = findViewById(R.id.email)
        val refPhone: EditText = findViewById(R.id.telefono)

        val btnSend: Button = findViewById(R.id.button2)
        btnSend.setOnClickListener {
            Log.d("MainActivity", refName.text.toString())
            Log.d("MainActivity", refLastName.text.toString())
            Log.d("MainActivity", refAge.text.toString())
            Log.d("MainActivity", refEmail.text.toString())
            Log.d("MainActivity", refPhone.text.toString())

            if (refName.text.isNotEmpty() && refLastName.text.isNotEmpty() && refAge.text.isNotEmpty() && refEmail.text.isNotEmpty() && refPhone.text.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("nombre", refName.text.toString())
                    putString("apellido", refLastName.text.toString())
                    putString("telefono", refPhone.text.toString())
                    putString("edad", refAge.text.toString())
                    putString("mail", refEmail.text.toString())
                    apply()
                }
                val intent = Intent(this, pantalla2::class.java)
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        val stringArrayCarreras = arrayOf("Ingeniería", "Medicina", "Licenciatura")
        val spinnerCarreras: Spinner = findViewById(R.id.spn2)
        val adapterCarreras = ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArrayCarreras)
        adapterCarreras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarreras.adapter = adapterCarreras

        // Llamada a la API de estados
        RetrofitClient.instance.getStates().enqueue(object : Callback<StateResponse> {
            override fun onResponse(call: Call<StateResponse>, response: Response<StateResponse>) {
                if (response.isSuccessful) {
                    // Ver respuesta cruda para depuración
                    Log.d("MainActivity", "Respuesta cruda: ${response.body().toString()}")

                    // Extraer lista de estados
                    val estados = response.body()?.Estados ?: listOf()
                    Log.d("MainActivity", "Estados: $estados")

                    // Configurar el Spinner con la lista de estados
                    val spinnerEstado: Spinner = findViewById(R.id.spn1)
                    val adapterEstado = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, estados)
                    adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerEstado.adapter = adapterEstado
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("MainActivity", "Error en la respuesta de la API: $errorBody")
                    Toast.makeText(this@MainActivity, "Error en la respuesta de la API: $errorBody", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<StateResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al consumir la API: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Error al consumir la API", t)
            }
        })

        // Llamada a la API de departamentos
        RetrofitClient.Apidepartamento.getDepartments().enqueue(object : Callback<DepartmentResponse> {
            override fun onResponse(call: Call<DepartmentResponse>, response: Response<DepartmentResponse>) {
                if (response.isSuccessful) {
                    val departamentos = response.body()?.departamentos ?: listOf()
                    val spinnerDepartamentos: Spinner = findViewById(R.id.spn2)
                    val adapterDepartamentos = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, departamentos)
                    adapterDepartamentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerDepartamentos.adapter = adapterDepartamentos
                } else {
                    Toast.makeText(this@MainActivity, "Error en la respuesta de la API: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DepartmentResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al consumir la API: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Error al consumir la API", t)
            }
        })

        // Llamada a la API de carreras universitarias
        RetrofitClient.careerApi.getCareers().enqueue(object : Callback<CareerResponse> {
            override fun onResponse(call: Call<CareerResponse>, response: Response<CareerResponse>) {
                if (response.isSuccessful) {
                    val carreras = response.body()?.carreras ?: listOf()
                    Log.d("MainActivity", "Carreras recibidas: $carreras")

                    val spinnerCarreras: Spinner = findViewById(R.id.spn33)
                    val adapterCarreras = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, carreras)
                    adapterCarreras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerCarreras.adapter = adapterCarreras
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("MainActivity", "Error en la respuesta de la API de carreras: $errorBody")
                    Toast.makeText(this@MainActivity, "Error en la respuesta de la API de carreras: $errorBody", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CareerResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al consumir la API: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Error al consumir la API", t)
            }
        })
    }
}
