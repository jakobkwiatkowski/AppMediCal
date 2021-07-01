package edu.ib.medical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddDocActivity : AppCompatActivity() {
    lateinit var edName: EditText
    lateinit var edSpec: EditText
    lateinit var edPhone: EditText
    lateinit var edEmail: EditText
    lateinit var edAdress: EditText
    lateinit var edCity: EditText

    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn: Button
    private lateinit var sqliteHelper: DocDatabaseHelper

    private var adapter: DocAdapter? = null
    private var doc: DocModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adddoctor)

        initView()

        sqliteHelper = DocDatabaseHelper(this)
        addBtn.setOnClickListener { addDoc() }


        adapter?.setOnClickUpdateItem { updateDoc() }


    }


         fun addDoc() {
        val name = edName.text.toString()
        val spec = edSpec.text.toString()
        val phone = edPhone.text.toString()
        val email = edEmail.text.toString()
        val adress = edAdress.text.toString()
        val city = edCity.text.toString()

        if (name.isEmpty() || spec.isEmpty() || phone.isEmpty() || email.isEmpty() || adress.isEmpty() || city.isEmpty()) {
            Toast.makeText(this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show()
            return
        } else {
            val doc = DocModel(
                1,
                name = name,
                spec = spec,
                phone = phone,
                email = email,
                adress = adress,
                city = city
            )
            val status = sqliteHelper.addDoc(doc)

            if (status > -1) {
                Toast.makeText(this, "Dodano lekarza!", Toast.LENGTH_SHORT).show()
                clearEditText()
//                DocListActivity().getDoc()
            } else {
                Toast.makeText(this, "Błąd dodawania lekarza", Toast.LENGTH_LONG).show()
            }
        }

    }

         fun updateDoc() {
        val name = edName.text.toString()
        val spec = edSpec.text.toString()
        val phone = edPhone.text.toString()
        val email = edEmail.text.toString()
        val adress = edAdress.text.toString()
        val city = edCity.text.toString()

        if (name == doc?.name && spec == doc?.spec && phone == doc?.phone && email == doc?.email && adress == doc?.adress && city == doc?.city) {
            Toast.makeText(this, "Dane nie zostały zmienione", Toast.LENGTH_SHORT).show()
            return
        }
        if (doc == null) return
        val doc = DocModel(
            id = doc!!.id,
            name = name,
            spec = spec,
            phone = phone,
            email = email,
            adress = adress,
            city = city
        )
        val status = sqliteHelper.updateDoc(doc)
        if (status > -1) {
            clearEditText()

//            DocListActivity().getDoc()


        } else {
            Toast.makeText(this, "Aktualizacja nieduana", Toast.LENGTH_SHORT).show()
        }
    }



   fun clearEditText() {
        edName.setText("")
        edSpec.setText("")
        edPhone.setText("")
        edEmail.setText("")
        edAdress.setText("")
        edCity.setText("")
    }




    private fun initView() {
        edName = findViewById(R.id.docName)
        edSpec = findViewById(R.id.docSpec)
        edPhone = findViewById(R.id.docPhone)
        edEmail = findViewById(R.id.docEmail)
        edAdress = findViewById(R.id.docAdress)
        edCity = findViewById(R.id.docCity)
        addBtn = findViewById(R.id.saveDoc)

    }

}