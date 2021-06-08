package edu.ib.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

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
            val intent = Intent(this, AddDocActivity::class.java)
            startActivity(intent)
        }
    }
}