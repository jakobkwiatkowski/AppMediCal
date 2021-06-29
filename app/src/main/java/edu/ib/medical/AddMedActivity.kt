package edu.ib.medical

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat

import java.util.*


class AddMedActivity : AppCompatActivity() {


    private lateinit var edNazwa: EditText
    private lateinit var edDawka: EditText
    private lateinit var edData: TextView
    private lateinit var edCzestotliwosc: TextView
    private lateinit var edIleRazy: TextView
    private lateinit var edGodzina: TextView
    private lateinit var edZapas: EditText
    private lateinit var edKoniec: TextView

    private lateinit var btnAdd: Button


    private lateinit var sqliteHelper: MyDataBaseHelper

    private var adapter: LekAdapter? = null
    private var lek: LekModel? = null

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


        initView()
        sqliteHelper = MyDataBaseHelper(this)

        btnAdd.setOnClickListener { addLek() }

        adapter?.setOnClickItem {
            Toast.makeText(this, it.nazwa, Toast.LENGTH_SHORT).show()

            edNazwa.setText(it.nazwa)
            edDawka.setText(it.dawka)
            edData.setText(it.data)
            edCzestotliwosc.setText(it.czestotliwosc)
            edIleRazy.setText(it.ileRazy).toString()
            edGodzina.setText(it.godzina)
            edZapas.setText(it.zapas)
            edKoniec.setText(it.koniec)
            lek = it
        }

        adapter?.setOnClickUpdateItem {
            updateLek()
        }


    }


    private fun addPrzypomnienie() {


        val rezultat = findViewById<TextView>(R.id.koniec_leku)
        val spinner5: Spinner = findViewById(R.id.przypomnij)

        val przypomnienie = arrayOf(
            "Przypomnij:",
            "2 dni przed końcem",
            "3 dni przed końcem",
            "4 dni przed końcem",
            "5 dni przed końcem",
            "6 dni przed końcem",
            "7 dni przed końcem"
        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, przypomnienie)

        spinner5.adapter = arrayAdapter
        spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                rezultat.text = przypomnienie[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    private fun addIle_razy() {

        val rezultat = findViewById<TextView>(R.id.ile_razy)
        val spinner3: Spinner = findViewById(R.id.ile)
        val ile_razy = arrayOf("1", "2", "3", "4", "5")
        val arrayAdapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, ile_razy)

        spinner3.adapter = arrayAdapter3
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                rezultat.text = ile_razy[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun addCzestotliowsc() {

        val rezultat = findViewById<TextView>(R.id.czestotliwosc)
        val spinner2: Spinner = findViewById(R.id.czesto)
        val czestotliwosc = arrayOf(
            "Jak często:",
            "codziennie",
            "co 2 dni",
            "co 3 dni",
            "co 4 dni",
            "co 5 dni",
            "co 6 dni",
            "co tydzień",
            "co 2 tygodnie",
            "co miesiąc"
        )
        val arrayAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, czestotliwosc)

        spinner2.adapter = arrayAdapter2
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                rezultat.text = czestotliwosc[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }


    private fun addData() {

        val data: TextView = findViewById(R.id.wybierzdate)
        val kalendarz: Calendar = Calendar.getInstance()
        val format: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val wyswietl = format.format(kalendarz.getTime())
        data.setText(wyswietl)
    }

    private fun addGodzina() {

        val godzina: TextView = findViewById(R.id.wybierzgodzine)
        val kalendarz: Calendar = Calendar.getInstance()
        val format: SimpleDateFormat = SimpleDateFormat("HH:mm")
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
            DatePickerDialog(
                this,
                dpd,
                kalendarz.get(Calendar.YEAR),
                kalendarz.get(Calendar.MONTH),
                kalendarz.get(Calendar.DAY_OF_MONTH)
            ).show()
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

            TimePickerDialog(
                this,
                dpd2,
                kalendarz.get(Calendar.HOUR_OF_DAY),
                kalendarz.get(Calendar.MINUTE),
                true
            ).show()
        }

    }


    private fun addLek() {


        val nazwa = edNazwa.text.toString()
        val dawka = edDawka.text.toString()
        val data = edData.text.toString()
        val czestotliwosc = edCzestotliwosc.text.toString()
        val ileRazy = edIleRazy.text.toString()
        val godzina = edGodzina.text.toString()
//        val przypomnienie = edPrzypomnienie.text.toString()
        val zapas = edZapas.text.toString()
        val koniec = edKoniec.text.toString()

        if (nazwa.isEmpty() || dawka.isEmpty() || data.isEmpty() || czestotliwosc.isEmpty() || ileRazy.isEmpty()
            || godzina.isEmpty() || zapas.isEmpty() || koniec.isEmpty()
        ) {

            Toast.makeText(this, "Proszę wypełnić wszystkie pola", Toast.LENGTH_SHORT).show()
        } else {
            val lek = LekModel(
                1, nazwa = nazwa, dawka = dawka, data = data, czestotliwosc = czestotliwosc,
                ileRazy = ileRazy, godzina = godzina, zapas = zapas, koniec = koniec
            )

            val status = sqliteHelper.addLek(lek)

            if (status > -1) {
                Toast.makeText(this, "Dodano lek!", Toast.LENGTH_SHORT).show()
                clearEditText()

            } else {
                Toast.makeText(this, "Nie dodano leku", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun updateLek() {
        val nazwa = edNazwa.text.toString()
        val dawka = edDawka.text.toString()
        val data = edData.text.toString()
        val czestotliwosc = edCzestotliwosc.text.toString()
        val ileRazy = edIleRazy.text.toString()
        val godzina = edGodzina.text.toString()
//        val przypomnienie = edPrzypomnienie.text.toString()
        val zapas = edZapas.text.toString()
        val koniec = edKoniec.text.toString()

        if (nazwa == lek?.nazwa && dawka == lek?.dawka && data == lek?.data && czestotliwosc == lek?.czestotliwosc.toString()
            && ileRazy == lek?.ileRazy.toString() && godzina == lek?.godzina
            && zapas == lek?.zapas && koniec == lek?.koniec.toString()
        ) {

            Toast.makeText(this, "Dane nie zostały zmienione", Toast.LENGTH_SHORT).show()
            return
        }
        if (lek == null) return
        val lek = LekModel(
            id = lek!!.id, nazwa = nazwa, dawka = dawka, data = data, czestotliwosc = czestotliwosc,
            ileRazy = ileRazy, godzina = godzina, zapas = zapas, koniec = koniec
        )

        val status = sqliteHelper.updateLek(lek)
        if (status > -1) {
            clearEditText()

        } else {
            Toast.makeText(this, "Aktualizacja nieudana", Toast.LENGTH_SHORT).show()
        }
    }


    private fun clearEditText() {
        edNazwa.setText("")
        edDawka.setText("")
        edData.setText("")
        edCzestotliwosc.setText("")
        edIleRazy.setText("")
        edGodzina.setText("")
//        edPrzypomnienie.setText("")
        edZapas.setText("")
        edKoniec.setText("")
        edNazwa.requestFocus()
    }


    private fun initView() {
        edNazwa = findViewById(R.id.nazwa_leku)
        edDawka = findViewById(R.id.dawka)
        edData = findViewById(R.id.wybierzdate)
        edCzestotliwosc = findViewById(R.id.czestotliwosc)
        edIleRazy = findViewById(R.id.ile_razy)
        edGodzina = findViewById(R.id.wybierzgodzine)
//        edPrzypomnienie = findViewById(R.id.switch1)
        edZapas = findViewById(R.id.zapas)
        edKoniec = findViewById(R.id.koniec_leku)
        btnAdd = findViewById(R.id.zapisz)

    }


}






