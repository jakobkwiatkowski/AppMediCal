package edu.ib.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DocListActivity : AppCompatActivity() {

    companion object { lateinit var sqliteHelper: DocDatabaseHelper }

    private lateinit var recyclerView: RecyclerView
    private var adapter: DocAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listdoctor)

        goMenu()
        goAddDoc()

        sqliteHelper = DocDatabaseHelper(this)
        initView()
        initRecyclerView()
        getDoc()
        adapter?.setOnClickDeleteItem { deleteDoc(it.id) }


    }

   fun getDoc() {

        val docList = sqliteHelper.getAllDocs()
        adapter?.addItems(docList)


    }

    private fun goMenu() {
        val menuButton = findViewById<ImageView>(R.id.menuButton4)
        menuButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goAddDoc() {
        val addDocButton = findViewById<Button>(R.id.addDocButton)
        addDocButton.setOnClickListener {
            val intent = Intent(this, AddDocActivity::class.java)
            startActivity(intent)
        }
    }



    private fun deleteDoc(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Czy na pewno usunąć lekarza?")
        builder.setCancelable(true)
        builder.setPositiveButton("tak") { dialog, _ ->
            var sqliteHelper:DocDatabaseHelper
            sqliteHelper = DocDatabaseHelper(this)
            sqliteHelper.deleteDocById(id)
            getDoc()
            dialog.dismiss()
        }
        builder.setNegativeButton("nie") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DocAdapter(this)
        recyclerView.adapter = adapter
    }


    private fun initView() {
        recyclerView = findViewById(R.id.doc_recycler)

    }


}
