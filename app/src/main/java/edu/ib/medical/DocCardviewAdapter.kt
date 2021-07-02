package edu.ib.medical

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class DocAdapter(con: Context): RecyclerView.Adapter<DocAdapter.DocViewHolder>() {

    val con = con

    private var docList: ArrayList<DocModel> = ArrayList()
    private var onClickDeleteItem:((DocModel) -> Unit)? = null


    fun addItems(items: ArrayList<DocModel>){
        this.docList = items
        notifyDataSetChanged()
    }


    fun setOnClickDeleteItem(callback: (DocModel) -> Unit){
        this.onClickDeleteItem = callback
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

        holder.btnUpdate.setOnClickListener {
            val inflater: LayoutInflater = LayoutInflater.from(con)
            val view: View = inflater.inflate(R.layout.updatedoc, null)

            val editName:TextView = view.findViewById(R.id.docNameedit)
            val editSpec: TextView = view.findViewById(R.id.docSpecedit)
            val editPhone: TextView = view.findViewById(R.id.docPhoneedit)
            val editEmail: TextView = view.findViewById(R.id.docEmailedit)
            val editAdress: TextView = view.findViewById(R.id.docAdressedit)
            val editCity: TextView = view.findViewById(R.id.docCityedit)

            editName.text = doc.name
            editSpec.text = doc.spec
            editPhone.text = doc.phone
            editEmail.text = doc.email
            editAdress.text = doc.adress
            editCity.text = doc.city

            val builder = AlertDialog.Builder(con)
                .setTitle("Aktualizuj dane lekarza")
                .setView(view)
                .setPositiveButton("Aktualizuj", DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate: Boolean = DocListActivity.sqliteHelper.updateDoc(doc.id,
                    editName.text.toString(),
                    editSpec.text.toString(),
                    editPhone.text.toString(),
                    editEmail.text.toString(),
                    editAdress.text.toString(),
                    editCity.text.toString())

                    if (isUpdate == true){
                        docList[position].name = editName.text.toString()
                        docList[position].spec = editSpec.text.toString()
                        docList[position].phone = editPhone.text.toString()
                        docList[position].email = editEmail.text.toString()
                        docList[position].adress = editAdress.text.toString()
                        docList[position].city = editCity.text.toString()
                        notifyDataSetChanged()
                        Toast.makeText(con, "Aktualizacja udana", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(con, "Błąd aktualizacji", Toast.LENGTH_SHORT).show()
                    }

                }).setNegativeButton("Zamknij", DialogInterface.OnClickListener { dialog, which ->

                })
            val alert = builder.create()
            alert.show()

        }

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



    }
}