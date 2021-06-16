package edu.ib.medical

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MedListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listmed)
        goMenu()
        goAddMed()

    }
    private fun goMenu() {
        val menuButton = findViewById<ImageView>(R.id.menuButton2)
        menuButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
    private fun goAddMed(){
        val addMedButton = findViewById<Button>(R.id.addMedButton)
        addMedButton.setOnClickListener {
            val intent = Intent(this, AddMedActivity::class.java)
            startActivity(intent)
        }
    }

    fun DodajDoBazy(){

        val myDB: MyDataBaseHelper
        val lista = ArrayList<String>()
        myDB = MyDataBaseHelper(this)

    }




//    override fun onResume() {
//        super.onResume()
//
//        val recycler_view:RecyclerView = findViewById(R.id.recycler_view)
//
//        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
//        recycler_view.adapter = CardViewAdapter(applicationContext)
//
//    }



}