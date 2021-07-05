package edu.ib.medical

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class medAdapter(con: Context): RecyclerView.Adapter<medAdapter.medViewHolder>(){
    val con = con

    private var medlist: ArrayList<LekModel> = ArrayList()


    fun addItems(items: ArrayList<LekModel>){
        this.medlist = items
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = medViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_lek_cardview_adapter, parent, false)
    )

    override fun onBindViewHolder(holder: medAdapter.medViewHolder, position: Int) {
        val med = medlist[position]

            holder.nazwa.text = med.nazwa
            holder.godz.text = med.godzina
            holder.dawka.text = med.dawka



    }

    override fun getItemCount(): Int {
        return medlist.size
    }

    class medViewHolder(var view: View): RecyclerView.ViewHolder(view){
        var nazwa = view.findViewById<TextView>(R.id.nazwa_txt)
        var godz = view.findViewById<TextView>(R.id.godz_txt)
        var dawka = view.findViewById<TextView>(R.id.dawka_txt)

    }


}