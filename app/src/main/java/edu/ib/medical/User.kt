package edu.ib.medical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class User : AppCompatActivity() {

    private lateinit var edNameUser: EditText
    private lateinit var btnAddUser: Button
    private lateinit var btnExit: Button
    private lateinit var mysqliteHelper: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initView()
        mysqliteHelper = UserDatabase(this)
        goMA()
        btnAddUser.setOnClickListener { addUser() }
    }

    private fun addUser() {
        val nameofuser = edNameUser.text.toString()

        if (nameofuser.isEmpty()) {
            Toast.makeText(this, "Proszę wpisać nazwę użytkownika", Toast.LENGTH_SHORT).show()
        } else {
            val status = mysqliteHelper.addUser(nameofuser)

            if (status > -1) {
                Toast.makeText(this, "Dodano użytkownika", Toast.LENGTH_SHORT).show()
                clearEditText()
                btnExit.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Nie dodano użytkownika", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        edNameUser.setText("")
    }

    private fun initView() {
        edNameUser = findViewById(R.id.editusername)
        btnAddUser = findViewById(R.id.zapbutton)
        btnExit = findViewById(R.id.exitbutton)
    }

    private fun goMA() {
        val btnExit = findViewById<Button>(R.id.exitbutton)
        btnExit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}