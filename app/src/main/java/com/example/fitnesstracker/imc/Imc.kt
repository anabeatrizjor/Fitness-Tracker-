package com.example.fitnesstracker.imc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.fitnesstracker.MainActivity
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.Calculo
import com.example.fitnesstracker.data.CalculoDataBase
import kotlinx.coroutines.launch
import java.util.Date

class Imc : AppCompatActivity() {

    private lateinit var nome : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        nome = findViewById(R.id.inputNome)
        val inputPeso = findViewById<EditText>(R.id.inputPeso)
        val inputAltura = findViewById<EditText>(R.id.inputAltura)
        val buttonCalcularIMC = findViewById<Button>(R.id.buttonCalcularIMC)
        val buttonLimparCampos = findViewById<Button>(R.id.buttonLimparCampos)

        val backArrow = findViewById<ImageView>(R.id.backArrowIMC)

        backArrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        buttonCalcularIMC.setOnClickListener {
            val nomeInput = nome.text.toString()
            val pesoText = inputPeso.text.toString()
            val alturaText = inputAltura.text.toString()

            if (pesoText.isNotEmpty() && alturaText.isNotEmpty()) {
                val peso = pesoText.toFloat()
                val altura = alturaText.toFloat() / 100

                val imc = calcularIMC(peso, altura)
                val classificacao = obterClassificacaoIMC(imc)

                exibirResultadoIMC(imc, classificacao)
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Erro")
                    .setMessage("Por favor, insira o peso e a altura.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        buttonLimparCampos.setOnClickListener {
            inputPeso.text.clear()
            inputAltura.text.clear()
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.greenDark)
    }

    private fun calcularIMC(peso: Float, altura: Float): Float {
        return peso / (altura * altura)
    }

    private fun obterClassificacaoIMC(imc: Float): String {
        return when {
            imc < 16 -> "Severamente abaixo do peso"
            imc < 17 -> "Moderadamente abaixo do peso"
            imc < 18.5 -> "Abaixo do peso"
            imc < 25 -> "Peso normal"
            imc < 30 -> "Sobrepeso"
            imc < 35 -> "Obesidade grau I"
            imc < 40 -> "Obesidade grau II (severa)"
            else -> "Obesidade grau III (mórbida)"
        }
    }

    private fun exibirResultadoIMC(imc: Float, classificacao: String) {
        val mensagem = "Seu IMC é %.2f.\nClassificação: %s".format(imc, classificacao)

        // Obtenha o nome do EditText
        val nomeInput = nome.text.toString()

        val calculo = Calculo(
            tipo = "IMC",
            resultado = mensagem,
            data = Date()
        )

        lifecycleScope.launch {
            CalculoDataBase.getDataBase(applicationContext).calculoDao().inserir(calculo)
        }

        AlertDialog.Builder(this)
            .setTitle("Resultado do IMC")
            .setMessage(mensagem)
            .setPositiveButton("OK", null)
            .show()
    }


}
