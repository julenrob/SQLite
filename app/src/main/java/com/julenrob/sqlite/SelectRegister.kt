package com.julenrob.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.julenrob.sqlite.databinding.ActivitySelectRegisterBinding

class SelectRegister : AppCompatActivity() {
    lateinit var binding : ActivitySelectRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBorrar.setOnClickListener {
            var amigosDBHelper = MySQLHelper(this)

            var borrado : Int = 0
            var input = binding.etId.text.toString().trim()
            if (input.isNullOrBlank()){
                borrado = 0
            } else {
                borrado = amigosDBHelper.borrarDato(binding.etId.text.toString().toInt())
            }

            println("--- " + borrado)
            if (borrado != 0){
                Toast.makeText(this, "success delete", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "failed delete, maybe missing fields", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonActualizar.setOnClickListener {
            var amigosDBHelper = MySQLHelper(this)

            var actualizado : Int = 0

            var input = binding.etId.text.toString().trim()
            if (input.isNullOrBlank()){

                actualizado = amigosDBHelper.actualizarDato(

                    0,
                    binding.etNewName.text.toString(),
                    binding.etNewEmail.text.toString()
                )
            } else {
                actualizado = amigosDBHelper.actualizarDato(

                    binding.etId.text.toString().toInt(),
                    binding.etNewName.text.toString(),
                    binding.etNewEmail.text.toString()
                )

            }


            println("--- " + actualizado)
            if (actualizado != 0){
                Toast.makeText(this, "success update", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "failed update, maybe missing fields", Toast.LENGTH_LONG).show()
            }
        }


    }
}