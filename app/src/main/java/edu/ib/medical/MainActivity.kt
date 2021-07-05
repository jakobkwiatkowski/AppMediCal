package edu.ib.medical

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {lateinit var sqliteHelper: MyDataBaseHelper}

    private lateinit var recyclerView: RecyclerView
    private var adapter: medAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goMenu()
        goAddMed()
        addData()
        addUserName()

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


        val medlist = sqliteHelper.getAllLeki2()
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

    fun addUserName(){

        var username = findViewById<TextView>(R.id.username)
        val btnUser = findViewById<ImageButton>(R.id.user)
        btnUser.setOnClickListener {


            val inflater: LayoutInflater = LayoutInflater.from(this)
            val view: View = inflater.inflate(R.layout.username, null)

            val editusername: EditText = view.findViewById(R.id.editusername)



            val builder = AlertDialog.Builder(this)
                .setTitle("Wprowadź nazwę użytkownika")
                .setView(view)
                .setPositiveButton("Zapisz", DialogInterface.OnClickListener { dialog, which ->

                    username.text = editusername.text.toString()

                    Toast.makeText(this, "Nazwa została wprowadzona", Toast.LENGTH_SHORT).show()

                }).setNegativeButton("Zamknij", DialogInterface.OnClickListener { dialog, which ->
                })
        val alert = builder.create()
           alert.show()

        }

    }

}
