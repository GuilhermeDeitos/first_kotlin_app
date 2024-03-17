package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.text.Editable
import android.text.TextWatcher
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNome = findViewById<EditText>(R.id.etNome)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnInverter = findViewById<Button>(R.id.btnInverter)
        val btnPalindromo = findViewById<Button>(R.id.btnPalindromo)
        val btnCriptografar = findViewById<Button>(R.id.btnCriptografar)

                etNome.addTextChangedListener( object : TextWatcher{
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        Log.d("MainActivity", "beforeTextChanged called")
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        Log.d("MainActivity", "afterTextChanged called")
                        if(checkField(etNome.text.toString())){
                            Log.d("MainActivity", "Text is not empty, enabling buttons")
                            btnInverter.isEnabled = true
                            btnPalindromo.isEnabled = true
                            btnCriptografar.isEnabled = true
                        }

                    }

                    override fun afterTextChanged(s: Editable) {
                        Log.d("MainActivity", "afterTextChanged called")
                    }
                })


        btnInverter.setOnClickListener {
            tvResult.visibility = TextView.VISIBLE
            btnInverterOnClick(etNome.text.toString(),tvResult)
        }

        btnPalindromo.setOnClickListener {
            tvResult.visibility = TextView.VISIBLE
            btnPalindromoOnClick(etNome.text.toString(),tvResult)
        }

        btnCriptografar.setOnClickListener {
            tvResult.visibility = TextView.VISIBLE
            btnCriptografarOnClick(etNome.text.toString(),tvResult)
        }
    }


    fun checkField(nome: String): Boolean {
        return nome.isNotEmpty()
    }

    fun btnPalindromoOnClick(nome: String, tvResult: TextView) {
        val nomeInvertido = nome?.reversed()
        if(nome == nomeInvertido){
            tvResult.text = "É um palíndromo"
        } else {
            tvResult.text = "Não é um palíndromo"
        }
    }

    fun btnInverterOnClick(nome: String, tvResult: TextView) {
        val nomeInvertido = nome?.reversed()
        tvResult.text = nomeInvertido
    }


    fun btnCriptografarOnClick(nome: String, tvResult: TextView) {
        var nomeCriptografado = ""
        val alfabeto = "abcdefghijklmnopqrstuvwxyz"
        for (i in 0..nome.length-1) {
            val letra = lowerCase(nome[i])
            val posicao = alfabeto.indexOf(letra)
            val novaPosicao = (posicao + 3) % 26
            val novaLetra = alfabeto[novaPosicao]
            nomeCriptografado += novaLetra
        }
        tvResult.text = nomeCriptografado
    }


    private fun lowerCase(c: Char): Char {
        return if (c in 'A'..'Z') c + 32 else c
    }


}