package edu.ib.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DocListActivity : AppCompatActivity() {

    private lateinit var sqliteHelper: DocDatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: DocAdapter? = null
    private var doc: DocModel? = null

    private lateinit var viewBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listdoctor)
        goMenu()
        goAddDoc()
        initView()
        initRecyclerView()
        viewBtn.setOnClickListener { getDoc() }

        sqliteHelper = DocDatabaseHelper(this)

        adapter?.setOnClickDeleteItem { deleteDoc(it.id) }

    }
    private fun goMenu() {
        val menuButton = findViewById<ImageView>(R.id.menuButton4)
        menuButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
    private fun goAddDoc(){
        val addDocButton = findViewById<Button>(R.id.addDocButton)
        addDocButton.setOnClickListener {
            val intent = Intent(this, AddDocActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getDoc(){
        val docList = sqliteHelper.getAllDocs()
        adapter?.addItems(docList)
    }

    private fun deleteDoc(id: Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Czy na pewno usunąć lekarza?")
        builder.setCancelable(true)
        builder.setPositiveButton("tak"){
            dialog, _ ->
            sqliteHelper.deleteDocById(id)
            getDoc()
            dialog.dismiss()
        }
        builder.setNegativeButton("nie"){
            dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DocAdapter()
        recyclerView.adapter = adapter

    }
    private fun initView(){
        recyclerView = findViewById(R.id.doc_recycler)
        viewBtn = findViewById(R.id.docView)
    }
}
