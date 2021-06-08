package edu.ib.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val menuButton = findViewById<Button>(R.id.button6)
        Go(menuButton, MainActivity())
        val medList = findViewById<Button>(R.id.button7)
        Go(medList, MedListActivity())
        val testResults = findViewById<Button>(R.id.button9)
        Go(testResults, TestResultsActivity())
        val docList = findViewById<Button>(R.id.button10)
        Go(docList, DocListActivity())
    }

    private fun Go(button: Button, Activity: AppCompatActivity) {

        button.setOnClickListener {
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }
    }

}
