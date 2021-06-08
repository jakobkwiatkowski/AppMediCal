package edu.ib.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class DocListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listdoctor)
        goMenu()
        goAddDoc()
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
}