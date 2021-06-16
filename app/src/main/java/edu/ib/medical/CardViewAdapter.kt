package edu.ib.medical

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

    class CardViewAdapter(val context: Context): RecyclerView.Adapter<MyViewHolder>(){
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
            val layoutInflater = LayoutInflater.from(p0.context)
            val cardView_med = layoutInflater.inflate(R.layout.card_view, p0, false)
            return MyViewHolder(cardView_med)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 30
        }

    }

    class MyViewHolder(val view:View): RecyclerView.ViewHolder(view)




