package edu.ib.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object{lateinit var sqliteHelper: MyDataBaseHelper}

    private lateinit var recyclerView: RecyclerView
    private var adapter: medAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goMenu()
        goAddMed()
        addData()

        sqliteHelper = MyDataBaseHelper(this)
        initView()
        initRecyclerView()
        getMed()

    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = medAdapter(this)
        recyclerView.adapter = adapter
    }

    private fun initView() {
        recyclerView = findViewById(R.id.LekRecycler)
    }

    private fun getMed() {
        val medlist = sqliteHelper.getAllLeki()
        adapter?.addItems(medlist)
    }



    private fun goMenu() {
        val menuButton = findViewById<ImageView>(R.id.menuButton)
        menuButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goAddMed(){
        val addMedButton = findViewById<Button>(R.id.addMedButton2)
        addMedButton.setOnClickListener {
            val intent = Intent(this, AddMedActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addData(){

        val data: TextView = findViewById(R.id.Data)
        val kalendarz: Calendar = Calendar.getInstance()
        val format: SimpleDateFormat =  SimpleDateFormat("dd-MM-yyyy")
        val wyswietl = format.format(kalendarz.getTime())
        data.setText(wyswietl)
    }



}
