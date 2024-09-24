package com.example.mysqliteapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var editTextUserName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var btnSave: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        editTextUserName = findViewById(R.id.editTextUserName)
        editTextEmail = findViewById(R.id.email)
        btnSave = findViewById(R.id.btnSave)

        // Inicializar DatabaseHelper
        dbHelper = DatabaseHelper(this)

        // Guardar datos en la base de datos al hacer clic en el botón
        btnSave.setOnClickListener {
            val username = editTextUserName.text.toString()
            val email = editTextEmail.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty()) {
                val result = dbHelper.insertUser(username, email)
                if (result != -1L) {
                    Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                    clearFields()
                } else {
                    Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        editTextUserName.text.clear()
        editTextEmail.text.clear()
    }
}
