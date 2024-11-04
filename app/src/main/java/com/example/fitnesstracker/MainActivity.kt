package com.example.fitnesstracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.data.MainItem
import com.example.fitnesstracker.imc.Imc
import com.example.fitnesstracker.tmb.Tmb

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var rvMainActivity: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.imc_ic,
                textStringId = R.string.label_imc,
                color = Color.GREEN
            )
        )

        mainItems.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.tmb_ic,
                textStringId = R.string.label_tmb,
                color = Color.GREEN
            )
        )

        val adapter = MainAdapter(mainItems, this)
        rvMainActivity = findViewById(R.id.rvMain)
        rvMainActivity.adapter = adapter
        rvMainActivity.layoutManager = GridLayoutManager(this, 2)

        window.statusBarColor = ContextCompat.getColor(this, R.color.greenDark)
    }

    override fun onClick(item: MainItem) {
        when (item.id) {
            1 -> { startActivity(Intent(this, Imc::class.java)) }
            2 -> { startActivity(Intent(this, Tmb::class.java)) }
        }
    }

    private inner class MainAdapter(
        private val mainItems: List<MainItem>,
        private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<MainViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent, listener)
        }

        override fun getItemCount(): Int {
            return mainItems.size
        }
    }

    private class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MainItem, listener: OnItemClickListener) {
            val image: ImageView = itemView.findViewById(R.id.imageView)
            val text: TextView = itemView.findViewById(R.id.textView)

            image.setImageResource(item.drawableId)
            text.setText(item.textStringId)

            itemView.setOnClickListener {
                listener.onClick(item)
            }
        }
    }
}
