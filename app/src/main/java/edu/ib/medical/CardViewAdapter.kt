package edu.ib.medical


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class LekAdapter: RecyclerView.Adapter<LekAdapter.LekViewHolder>(){

    private var lekList: ArrayList<LekModel> = ArrayList()
    private var onClickItem:((LekModel) ->Unit)? = null
    private var onClickDeleteItem:((LekModel) ->Unit)? = null
    private var onClickUpdateItem:((LekModel) ->Unit)? = null

    fun addItems(items: ArrayList<LekModel>) {
        this.lekList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (LekModel) -> Unit){
        this.onClickItem = callback
    }

    fun setOnClickDeleteItem(callback:(LekModel)->Unit){
        this.onClickDeleteItem = callback
    }

    fun setOnClickUpdateItem(callback:(LekModel) -> Unit) {
        this.onClickUpdateItem = callback

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LekViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
    )


    override fun onBindViewHolder(holder: LekViewHolder, position: Int) {
        val lek = lekList[position]
        holder.bindView(lek)
        holder.itemView.setOnClickListener {onClickItem?.invoke(lek)}
        holder.btnDelete.setOnClickListener {onClickDeleteItem?.invoke(lek)}
        holder.btnUpdate.setOnClickListener{onClickUpdateItem?.invoke(lek)}

    }

    override fun getItemCount(): Int {
        return lekList.size
    }


    class LekViewHolder(var view:View): RecyclerView.ViewHolder(view){

        private var id = view.findViewById<TextView>(R.id.id_leku_txt)
        private var nazwa = view.findViewById<TextView>(R.id.nazwa_leku_txt)
        private var dawka = view.findViewById<TextView>(R.id.dawka_leku_txt)
        private var data = view.findViewById<TextView>(R.id.data_rozpoczecia_txt)
        private var czestotliwosc =  view.findViewById<TextView>(R.id.czestotliwosc_txt)
        private var ileRazy =  view.findViewById<TextView>(R.id.ile_razy_txt)
        private var godzina =  view.findViewById<TextView>(R.id.godzina_txt)
        private var zapas =  view.findViewById<TextView>(R.id.zapas_tabletek_txt)
        private var koniec = view.findViewById<TextView>(R.id.koniec_leku_txt)
         var btnDelete = view.findViewById<ImageButton>(R.id.usun)
         var btnUpdate = view.findViewById<ImageButton>(R.id.zmien)


        fun bindView(lek:LekModel) {
            id.text = lek.id.toString()
            nazwa.text = lek.nazwa
            dawka.text = lek.dawka
            data.text = lek.data
            czestotliwosc.text = lek.czestotliwosc
            ileRazy.text = lek.ileRazy
            godzina.text = lek.godzina
            zapas.text = lek.zapas
            koniec.text = lek.koniec

        }

    }
}





