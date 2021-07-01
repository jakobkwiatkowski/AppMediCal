package edu.ib.medical

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MedListActivity : AppCompatActivity() {


    private lateinit var sqliteHelper: MyDataBaseHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: LekAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listmed)
        goMenu()
        goAddMed()



        initView()
        initRecyclerVew()

        sqliteHelper = MyDataBaseHelper(this)
        adapter?.setOnClickDeleteItem { deleteLek(it.id) }

        getLek()


    }

         fun getLek() {
        val lekList = sqliteHelper.getAllLeki()
        adapter?.addItems(lekList)
    }


    private fun goMenu() {
        val menuButton = findViewById<ImageView>(R.id.menuButton2)
        menuButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }


    private fun goAddMed() {
        val addMedButton = findViewById<Button>(R.id.addMedButton)
        addMedButton.setOnClickListener {
            val intent = Intent(this, AddMedActivity::class.java)
            startActivity(intent)
        }
    }





    private fun deleteLek(id: Int) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Czy na pewno chcesz usunąć ten lek?")
        builder.setCancelable(true)
        builder.setPositiveButton("Tak") { dialog, _ ->
            sqliteHelper.deleteLekById(id)
            getLek()
            dialog.dismiss()

        }
        builder.setNegativeButton("Nie") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }


    private fun initRecyclerVew() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LekAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_view)


    }


}










