package edu.ib.medical

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MedListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listmed)
        goMenu()
        goAddMed()
//        storeDataInArrays()
//        val recyclerView:RecyclerView = findViewById(R.id.recycler_view)
//        val cardViewAdapter = CardViewAdapter(this)
//        recyclerView.setAdapter(cardViewAdapter)



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



    val myDB = MyDataBaseHelper(this)
    val lek_id = ArrayList<String>()
    val nazwa_leku= ArrayList<String>()
    val dawka_leku = ArrayList<String>()
    val data = ArrayList<String>()
    val czestotliwosc = ArrayList<String>()
    val ile_razy = ArrayList<String>()
    val godzina = ArrayList<String>()
    val przypomnienie = ArrayList<String>()
    val zapas = ArrayList<String>()
    val koniec_leku = ArrayList<String>()

    fun storeDataInArrays(){
        val cursor:Cursor = myDB.readAllData()
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Brak danych", Toast.LENGTH_SHORT).show()
        }else{
            while(cursor.moveToNext()){
                lek_id.add(cursor.getString(0))
                nazwa_leku.add(cursor.getString(1))
                dawka_leku.add(cursor.getString(2))
                data.add(cursor.getString(3))
                czestotliwosc.add(cursor.getString(4))
                ile_razy.add(cursor.getString(5))
                godzina.add(cursor.getString(6))
                przypomnienie.add(cursor.getString(7))
                zapas.add(cursor.getString(8))
                koniec_leku.add(cursor.getString(9))
            }
        }
    }



}










