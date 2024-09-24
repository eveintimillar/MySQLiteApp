package com.example.mysqliteapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    private lateinit var editTextUserName: EditText
    private lateinit var btnSave: Button
    private lateinit var btnLoad: Button
    private lateinit var tvResult: TextView
    private lateinit var Second: Button // Cambié el nombre a btnSecond para mayor claridad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializar las vistas
        editTextUserName = findViewById(R.id.editTextUserName)
        btnSave = findViewById(R.id.btnSave)
        btnLoad = findViewById(R.id.btnLoad)
        tvResult = findViewById(R.id.tvResult)
        Second = findViewById(R.id.second) // Inicializamos el botón correctamente

        // Inicializar SharedPreferences
        val sharedPref = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Configurar botones
        btnSave.setOnClickListener {
            saveData(sharedPref)
        }

        Second.setOnClickListener { // Cambié 'second' a 'btnSecond'
            startActivity(Intent(this, SecondActivity::class.java))
        }

        btnLoad.setOnClickListener {
            loadData(sharedPref)
        }

        // Cargar datos automáticamente si existen
        loadData(sharedPref)
    }

    private fun saveData(sharedPref: android.content.SharedPreferences) {
        val userName = editTextUserName.text.toString().trim()
        if (userName.isEmpty()) {
            Toast.makeText(this, "Debe ingresar un nombre de usuario", Toast.LENGTH_SHORT).show()
            return
        }
        with(sharedPref.edit()) {
            putString("user_name", userName)
            apply()
        }
        tvResult.text = getString(R.string.stored_name) + " $userName"
        Toast.makeText(this, "Nombre guardado", Toast.LENGTH_SHORT).show()
    }

    private fun loadData(sharedPref: android.content.SharedPreferences) {
        val storedName = sharedPref.getString("user_name", "No name found") ?: "No name found"
        tvResult.text = getString(R.string.stored_name) + " $storedName"
        Toast.makeText(this, "Datos cargados", Toast.LENGTH_SHORT).show()
    }
}
