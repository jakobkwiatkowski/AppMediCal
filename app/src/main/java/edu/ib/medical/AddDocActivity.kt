package edu.ib.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class AddDocActivity : AppCompatActivity() {
   private lateinit var edName: EditText
   private lateinit var edSpec: EditText
   private lateinit var edPhone: EditText
   private lateinit var edEmail: EditText
   private lateinit var edAdress: EditText
   private lateinit var edCity: EditText

    private lateinit var addBtn: Button
    private lateinit var backBtn: Button
    private lateinit var sqliteHelper: DocDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adddoctor)

        goListaDoc()


        sqliteHelper = DocDatabaseHelper(this)
        initView()


        addBtn.setOnClickListener { addDoc() }

    }



     private fun addDoc() {
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
                     backBtn.setOnClickListener {
                         val intent = Intent(this, DocListActivity::class.java)
                         startActivity(intent)
                     }


                 } else {
                     Toast.makeText(this, "Błąd dodawania lekarza", Toast.LENGTH_LONG).show()
                 }
             }
         }






    private fun goListaDoc() {
        val addBack = findViewById<Button>(R.id.listadoc)
        addBack.setOnClickListener {
            val intent = Intent(this, DocListActivity::class.java)
            startActivity(intent)
        }

    }


    private fun initView() {
        edName = findViewById(R.id.docName)
        edSpec = findViewById(R.id.docSpec)
        edPhone = findViewById(R.id.docPhone)
        edEmail = findViewById(R.id.docEmail)
        edAdress = findViewById(R.id.docAdress)
        edCity = findViewById(R.id.docCity)
        addBtn = findViewById(R.id.saveDoc)
        backBtn = findViewById(R.id.listadoc)

    }

}