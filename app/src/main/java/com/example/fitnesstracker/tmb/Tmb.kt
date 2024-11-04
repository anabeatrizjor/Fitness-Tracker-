package com.example.fitnesstracker.tmb

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fitnesstracker.MainActivity
import com.example.fitnesstracker.R

class Tmb : AppCompatActivity() {

    private lateinit var inputNomeTMB: EditText
    private lateinit var inputIdade: EditText
    private lateinit var inputPesoTMB: EditText
    private lateinit var inputAlturaTMB: EditText
    private lateinit var genderGroup: RadioGroup
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private lateinit var buttonCalcularTMB: Button
    private lateinit var buttonLimparCamposTMB: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        val backArrow = findViewById<ImageView>(R.id.backArrowTMB)

        backArrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        inputNomeTMB = findViewById(R.id.inputNomeTMB)
        inputIdade = findViewById(R.id.inputIdade)
        inputPesoTMB = findViewById(R.id.inputPesoTMB)
        inputAlturaTMB = findViewById(R.id.inputAlturaTMB)
        genderGroup = findViewById(R.id.genderGroup)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
        buttonCalcularTMB = findViewById(R.id.buttonCalcularTMB)
        buttonLimparCamposTMB = findViewById(R.id.buttonLimparCamposTMB)

        buttonCalcularTMB.setOnClickListener {
            calcularTMB()
        }

        buttonLimparCamposTMB.setOnClickListener {
            limparCampos()
        }

        window.statusBarColor = ContextCompat.getColor(this, R.color.greenDark)
    }

    private fun calcularTMB() {
        val nome = inputNomeTMB.text.toString()
        val idade = inputIdade.text.toString().toIntOrNull()
        val peso = inputPesoTMB.text.toString().toDoubleOrNull()
        val altura = inputAlturaTMB.text.toString().toDoubleOrNull()
        val sexoMasculino = radioMale.isChecked
        val sexoFeminino = radioFemale.isChecked

        if (idade == null || peso == null || altura == null || (!sexoMasculino && !sexoFeminino)) {
            AlertDialog.Builder(this)
                .setTitle("Erro")
                .setMessage("Por favor, preencha todos os campos corretamente.")
                .setPositiveButton("OK", null)
                .show()
            return
        }

        val tmb = if (sexoMasculino) {
            88.36 + (13.4 * peso) + (4.8 * altura) - (5.7 * idade)
        } else {
            447.6 + (9.2 * peso) + (3.1 * altura) - (4.3 * idade)
        }

        val message = "$nome, sua Taxa Metabólica Basal (TMB) é aproximadamente ${"%.2f".format(tmb)} kcal por dia."

        AlertDialog.Builder(this)
            .setTitle("Resultado da TMB")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun limparCampos() {
        inputNomeTMB.text.clear()
        inputIdade.text.clear()
        inputPesoTMB.text.clear()
        inputAlturaTMB.text.clear()
        genderGroup.clearCheck()
    }
}
