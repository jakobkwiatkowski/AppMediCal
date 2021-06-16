package edu.ib.medical

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

class CardViewAdapter(var context:Context): Adapter<CardViewAdapter.MyViewHolder>() {

    var lek_id = ArrayList<String>()
    var nazwa_leku = ArrayList<String>()
    var dawka_leku = ArrayList<String>()
    var data = ArrayList<String>()
    var czestotliwosc = ArrayList<String>()
    var ile_razy = ArrayList<String>()
    var godzina = ArrayList<String>()
    var przypomnienie = ArrayList<String>()
    var zapas = ArrayList<String>()
    var koniec_leku = ArrayList<String>()


    fun CardAdapter(context: Context, lek_id: ArrayList<String>, nazwa_leku: ArrayList<String>,
                    dawka_leku: ArrayList<String>,
                    data: ArrayList<String>,
                    czestotliwosc: ArrayList<String>,
                    ile_razy: ArrayList<String>,
                    godzina: ArrayList<String>,
                    przypomnienie: ArrayList<String>,
                    zapas: ArrayList<String>,
                    koniec_leku: ArrayList<String>) {

        this.context = context
        this.lek_id = lek_id
        this.nazwa_leku = nazwa_leku
        this.dawka_leku = dawka_leku
        this.data = data
        this.czestotliwosc = czestotliwosc
        this.ile_razy = ile_razy
        this.godzina = godzina
        this.przypomnienie = przypomnienie
        this.zapas = zapas
        this.koniec_leku = koniec_leku


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val cardView_med: View = layoutInflater.inflate(R.layout.card_view, parent, false)
        return MyViewHolder(cardView_med)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.id_leku_txt = (lek_id.get(position))
        holder.nazwa_leku_txt = (nazwa_leku.get(position))
        holder.dawka_leku_txt = (dawka_leku.get(position))
        holder.data_rozpoczecia_txt = (data.get(position))
        holder.czestotliwosc_txt = (czestotliwosc.get(position))
        holder.ile_razy_txt = (ile_razy.get(position))
        holder.godzina_txt = (godzina.get(position))
        holder.koniec_leku_txt = (koniec_leku.get(position))
        holder.zapas_tabletek_txt = (zapas.get(position))
        holder.przypomnij_txt = (przypomnienie.get(position))
    }

    override fun getItemCount(): Int {
        return lek_id.size
    }


    class MyViewHolder(view: View) : ViewHolder(view) {
        var id_leku_txt = ""
        var nazwa_leku_txt = ""
        var dawka_leku_txt = ""
        var data_rozpoczecia_txt = ""
        var czestotliwosc_txt = ""
        var ile_razy_txt = ""
        var godzina_txt = ""
        var koniec_leku_txt = ""
        var zapas_tabletek_txt = ""
        var przypomnij_txt = ""

        fun MyViewHolder(itemView: View) {


            id_leku_txt = itemView.findViewById(R.id.id_leku_txt)
            nazwa_leku_txt = itemView.findViewById(R.id.nazwa_leku_txt)
            dawka_leku_txt = itemView.findViewById(R.id.dawka_leku_txt)
            data_rozpoczecia_txt = itemView.findViewById(R.id.data_rozpoczecia_txt)
            czestotliwosc_txt = itemView.findViewById(R.id.czestotliwosc_txt)
            ile_razy_txt = itemView.findViewById(R.id.ile_razy_txt)
            godzina_txt = itemView.findViewById(R.id.godzina_txt)
            koniec_leku_txt = itemView.findViewById(R.id.koniec_leku_txt)
            zapas_tabletek_txt = itemView.findViewById(R.id.zapas_tabletek_txt)
            przypomnij_txt = itemView.findViewById(R.id.przypomnij_txt)

        }
    }

}



