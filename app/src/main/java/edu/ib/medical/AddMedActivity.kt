package edu.ib.medical

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*
import android.content.Intent




class AddMedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addmed)

        addPrzypomnienie()
        addIle_razy()
        addCzestotliowsc()
        addData()
        addGodzina()
        addTermin()
        addCzas()
        goMenu()
    }
    private fun goMenu() {
        val menuButton = findViewById<ImageView>(R.id.menuButton6)
        menuButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addPrzypomnienie() {
        val spinner5: Spinner = findViewById(R.id.spinner5)

        val przypomnienie = arrayOf("2 dni przed końcem", "3 dni przed końcem", "4 dni przed końcem", "5 dni przed końcem", "6 dni przed końcem", "7 dni przed końcem")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, przypomnienie)

        spinner5.adapter = arrayAdapter
        spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    private fun addIle_razy() {

        val spinner3: Spinner = findViewById(R.id.spinner3)
        val ile_razy = arrayOf("1", "2", "3", "4", "5")
        val arrayAdapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, ile_razy)

        spinner3.adapter = arrayAdapter3
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun addCzestotliowsc() {

        val spinner2: Spinner = findViewById(R.id.spinner2)
        val czestotliwosc = arrayOf("codziennie", "co 2 dni", "co 3 dni", "co 4 dni", "co 5 dni", "co 6 dni", "co tydzień", "co 2 tygodnie", "co miesiąc")
        val arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, czestotliwosc)

        spinner2.adapter = arrayAdapter2
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }
    private fun addData(){

        val data: TextView = findViewById(R.id.wybierzdate)
        val kalendarz: Calendar = Calendar.getInstance()
        val format: SimpleDateFormat =  SimpleDateFormat("dd-MM-yyyy")
        val wyswietl = format.format(kalendarz.getTime())
        data.setText(wyswietl)
    }

    private fun addGodzina(){

        val godzina: TextView = findViewById(R.id.wybierzgodzine)
        val kalendarz: Calendar = Calendar.getInstance()
        val format: SimpleDateFormat =  SimpleDateFormat("HH:mm")
        val wyswietl = format.format(kalendarz.getTime())
        godzina.setText(wyswietl)
    }

    private fun addTermin() {

        val kalendarz = Calendar.getInstance()
        val wybierzdate: TextView = findViewById(R.id.wybierzdate)

        val kiedy: Button = findViewById(R.id.terminbtn)

        kiedy.setOnClickListener {
            val dpd = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                kalendarz.set(Calendar.YEAR, year)
                kalendarz.set(Calendar.MONTH, month)
                kalendarz.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                wybierzdate.text = SimpleDateFormat("dd-MM-yyy").format(kalendarz.time)

            }
            DatePickerDialog(this, dpd, kalendarz.get(Calendar.YEAR), kalendarz.get(Calendar.MONTH), kalendarz.get(Calendar.DAY_OF_MONTH)).show()
        }

    }






    private fun addCzas() {

        val kalendarz = Calendar.getInstance()


        val wybierzczas: TextView = findViewById(R.id.wybierzgodzine)
        val kiedy2: Button = findViewById(R.id.czas)

        kiedy2.setOnClickListener {
            val dpd2 = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->

                kalendarz.set(Calendar.HOUR_OF_DAY, hour)
                kalendarz.set(Calendar.MINUTE, minute)

                wybierzczas.text = SimpleDateFormat("HH:mm").format(kalendarz.time)
            }

            TimePickerDialog(this, dpd2, kalendarz.get(Calendar.HOUR_OF_DAY), kalendarz.get(Calendar.MINUTE), true).show()
        }

    }



}
