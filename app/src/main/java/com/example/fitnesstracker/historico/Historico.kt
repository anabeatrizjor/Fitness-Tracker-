package com.example.fitnesstracker.historico

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.MainActivity
import com.example.fitnesstracker.R
import com.example.fitnesstracker.data.CalculoAdapter
import com.example.fitnesstracker.data.CalculoDataBase
import kotlinx.coroutines.launch

class Historico : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalculoAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        val backArrow = findViewById<ImageView>(R.id.backArrowHistorico)

        backArrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        recyclerView = findViewById(R.id.rvHistorico)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val calculos = CalculoDataBase.getDataBase(applicationContext).calculoDao().getTodosCalculos()
            adapter = CalculoAdapter(calculos)
            recyclerView.adapter = adapter
        }

        window.statusBarColor = getColor(R.color.greenDark)

    }
}