package edu.ib.medical

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DocAdapter: RecyclerView.Adapter<DocAdapter.DocViewHolder>() {

    private var docList: ArrayList<DocModel> = ArrayList()
    private var onClickDeleteItem:((DocModel) -> Unit)? = null
    private var onClickUpdateItem:((DocModel) -> Unit)? = null

    fun addItems(items: ArrayList<DocModel>){
        this.docList = items
        notifyDataSetChanged()
    }



    fun setOnClickDeleteItem(callback: (DocModel) -> Unit){
        this.onClickDeleteItem = callback
    }

    fun setOnClickUpdateItem(callback: (DocModel) -> Unit) {
        this.onClickUpdateItem = callback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DocViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.doc_cardview, parent, false)
    )

    override fun onBindViewHolder(holder: DocViewHolder, position: Int) {
        val doc = docList[position]

        holder.id.text = doc.id.toString()
        holder.name.text = doc.name
        holder.spec.text = doc.spec
        holder.phone.text = doc.phone
        holder.email.text = doc.email
        holder.adress.text = doc.adress
        holder.city.text = doc.city


        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(doc) }
        holder.btnUpdate.setOnClickListener { onClickUpdateItem?.invoke(doc) }

    }



    override fun getItemCount():Int{
        return docList.size
    }


    class DocViewHolder(var view: View):RecyclerView.ViewHolder(view) {
        var id = view.findViewById<TextView>(R.id.docID_txt)
        var name = view.findViewById<TextView>(R.id.name_txt)
        var spec = view.findViewById<TextView>(R.id.spec_txt)
        var phone = view.findViewById<TextView>(R.id.phone_txt)
        var email = view.findViewById<TextView>(R.id.email_txt)
        var adress = view.findViewById<TextView>(R.id.adress_txt)
        var city = view.findViewById<TextView>(R.id.city_txt)
        var btnDelete = view.findViewById<ImageButton>(R.id.deleteDoc)
        var btnUpdate = view.findViewById<ImageButton>(R.id.editDoc)


//        fun bindView(doc: DocModel) {
//            id.text = doc.id.toString()
//            name.text = doc.name
//            spec.text = doc.spec
//            phone.text = doc.phone
//            email.text = doc.email
//            adress.text = doc.adress
//            city.text = doc.city
//        }
    }
}