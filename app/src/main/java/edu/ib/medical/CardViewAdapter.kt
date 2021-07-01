package edu.ib.medical


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class LekAdapter: RecyclerView.Adapter<LekAdapter.LekViewHolder>(){

    private var lekList: ArrayList<LekModel> = ArrayList()
    private var onClickDeleteItem:((LekModel) ->Unit)? = null
    private var onClickUpdateItem:((LekModel) ->Unit)? = null

    fun addItems(items: ArrayList<LekModel>) {
        this.lekList = items
        notifyDataSetChanged()
    }




    fun setOnClickDeleteItem(callback:(LekModel)->Unit){
        this.onClickDeleteItem = callback
    }

    fun setOnClickUpdateItem(callback:(LekModel)->Unit){
        this.onClickUpdateItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LekViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
    )


    override fun onBindViewHolder(holder: LekViewHolder, position: Int) {
        val lek = lekList[position]
       holder.id.text = lek.id.toString()
        holder.nazwa.text = lek.nazwa
        holder.dawka.text = lek.dawka
        holder.data.text = lek.data
        holder.czestotliwosc.text = lek.czestotliwosc
        holder.ileRazy.text = lek.ileRazy
        holder.godzina.text = lek.godzina
        holder.zapas.text = lek.zapas
        holder.koniec.text = lek.koniec


        holder.btnDelete.setOnClickListener {onClickDeleteItem?.invoke(lek)}
        holder.btnUpdate.setOnClickListener{onClickUpdateItem?.invoke(lek)}

    }

    override fun getItemCount(): Int {
        return lekList.size
    }


    class LekViewHolder(var view:View): RecyclerView.ViewHolder(view){

        var id = view.findViewById<TextView>(R.id.id_leku_txt)
        var nazwa = view.findViewById<TextView>(R.id.nazwa_leku_txt)
        var dawka = view.findViewById<TextView>(R.id.dawka_leku_txt)
        var data = view.findViewById<TextView>(R.id.data_rozpoczecia_txt)
        var czestotliwosc =  view.findViewById<TextView>(R.id.czestotliwosc_txt)
        var ileRazy =  view.findViewById<TextView>(R.id.ile_razy_txt)
        var godzina =  view.findViewById<TextView>(R.id.godzina_txt)
        var zapas =  view.findViewById<TextView>(R.id.zapas_tabletek_txt)
        var koniec = view.findViewById<TextView>(R.id.koniec_leku_txt)

        var btnDelete = view.findViewById<ImageButton>(R.id.usun)
        var btnUpdate = view.findViewById<ImageButton>(R.id.zmien)



    }
}





