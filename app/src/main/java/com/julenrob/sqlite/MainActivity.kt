package com.julenrob.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.julenrob.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var amigosDBHelper = MySQLHelper(this)

        binding.buttonGuardar.setOnClickListener {
            amigosDBHelper.addDato(binding.etNombre.text.toString(), binding.etEmail.text.toString())
        }

        binding.buttonConsultar.setOnClickListener {
            binding.tvResultado.text = ""
            val cursor = amigosDBHelper.mostrarDatos()
            if (cursor!!.moveToFirst()) {
                do {
                    binding.tvResultado.append(
                        cursor.getInt(0).toString() + ": ")
                    binding.tvResultado.append(
                        cursor.getString(1).toString()+ ", ")
                    binding.tvResultado.append(
                        cursor.getString(2).toString() + "\n")
                } while (cursor.moveToNext())
            }
        }

        binding.buttonBorrarActualizar.setOnClickListener {
            var intent = Intent(this, SelectRegister::class.java)
            startActivity(intent)
        }

    }
}