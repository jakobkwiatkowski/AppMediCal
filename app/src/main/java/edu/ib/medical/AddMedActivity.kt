package edu.ib.medical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


class AddMedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addmed)
        val spinner5:Spinner = findViewById(R.id.spinner5)

        val przypomnienie = arrayOf("2 dni przed końcem", "3 dni przed końcem", "4 dni przed końcem", "5 dni przed końcem", "6 dni przed końcem", "7 dni przed końcem")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, przypomnienie)



        spinner5.adapter = arrayAdapter

        spinner5.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val spinner3:Spinner = findViewById(R.id.spinner3)
        val ile_razy = arrayOf("1", "2", "3", "4", "5")
        val arrayAdapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, ile_razy)

        spinner3.adapter = arrayAdapter3
        spinner3.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        val spinner2:Spinner = findViewById(R.id.spinner2)
        val czestotliwosc = arrayOf("codziennie", "co 2 dni", "co 3 dni", "co 4 dni", "co 5 dni", "co 6 dni", "co tydzień", "co 2 tygodnie")
        val arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, czestotliwosc)

        spinner2.adapter = arrayAdapter2
        spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


    }
}