package edu.ib.medical

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class AddMedActivity : AppCompatActivity() {


    private lateinit var edNazwa: EditText
    private lateinit var edDawka: EditText
    private lateinit var edData: TextView
    private lateinit var edIleRazy: TextView
    private lateinit var edGodzina: TextView
    private lateinit var edZapas: TextView
    private lateinit var edKoniec: TextView

    private lateinit var btnAdd: Button
    private lateinit var btnBack: Button


    private lateinit var sqliteHelper: MyDataBaseHelper

    private var adapter: LekAdapter? = null
    private var lek: LekModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addmed)




        addPrzypomnienie()
        addIle_razy()
        addTermin()
        addCzas()
        addDatakoniec()
        goListaLek()

        initView()
        sqliteHelper = MyDataBaseHelper(this)

        btnAdd.setOnClickListener { addLek() }



    }


    private fun addPrzypomnienie() {


        val rezultat = findViewById<TextView>(R.id.koniec_leku)
        val spinner5: Spinner = findViewById(R.id.przypomnij)

        val przypomnienie = arrayOf(
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
        val ile_razy = arrayOf("raz dziennie", "2 razy dziennie", "3 razy dziennie")
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


    private fun addDatakoniec() {

        val kalendarz = Calendar.getInstance()
        val koniec: TextView = findViewById(R.id.zapas)

        val kiedy: Button = findViewById(R.id.koniecbtn)

        kiedy.setOnClickListener {
            val dpd = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                kalendarz.set(Calendar.YEAR, year)
                kalendarz.set(Calendar.MONTH, month)
                kalendarz.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                koniec.text = SimpleDateFormat("dd-MM-yyy").format(kalendarz.time)

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

        val ilosc = findViewById<TextView>(R.id.ile_razy).toString()


        val wybierzczas: TextView = findViewById(R.id.wybierzgodzine)
        val lista = mutableListOf<String>()

        var godzina = ""


        val kiedy2: Button = findViewById(R.id.czas)


        kiedy2.setOnClickListener {
            val dpd2 = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->

                kalendarz.set(Calendar.HOUR_OF_DAY, hour)
                kalendarz.set(Calendar.MINUTE, minute)

                godzina = SimpleDateFormat("HH:mm").format(kalendarz.time)
                lista.add(godzina)
                wybierzczas.text = (lista.toString().replace("[", "").replace("]", ""))
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
        val ileRazy = edIleRazy.text.toString()
        val godzina = edGodzina.text.toString()
        val zapas = edZapas.text.toString()
        val koniec = edKoniec.text.toString()

        if (nazwa.isEmpty() || dawka.isEmpty() || data.isEmpty() || ileRazy.isEmpty()
            || godzina.isEmpty() || zapas.isEmpty() || koniec.isEmpty()
        ) {

            Toast.makeText(this, "Proszę wypełnić wszystkie pola", Toast.LENGTH_SHORT).show()
        } else {
            val lek = LekModel(
                1, nazwa = nazwa, dawka = dawka, data = data,
                ileRazy = ileRazy, godzina = godzina, zapas = zapas, koniec = koniec
            )

            val status = sqliteHelper.addLek(lek)

            if (status > -1) {
                Toast.makeText(this, "Dodano lek!", Toast.LENGTH_SHORT).show()

                clearEditText()
                btnBack.setOnClickListener {
                    val intent = Intent(this, MedListActivity::class.java)
                    startActivity(intent)
                }


            } else {
                Toast.makeText(this, "Nie dodano leku", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun clearEditText() {
        edNazwa.setText("")
        edDawka.setText("")
        edData.setText("")
        edIleRazy.setText("")
        edGodzina.setText("")

        edZapas.setText("")
        edKoniec.setText("")
        edNazwa.requestFocus()
    }


    private fun initView() {
        edNazwa = findViewById(R.id.nazwa_leku)
        edDawka = findViewById(R.id.dawka)
        edData = findViewById(R.id.wybierzdate)
        edIleRazy = findViewById(R.id.ile_razy)
        edGodzina = findViewById(R.id.wybierzgodzine)

        edZapas = findViewById(R.id.zapas)
        edKoniec = findViewById(R.id.koniec_leku)
        btnAdd = findViewById(R.id.zapisz)
        btnBack = findViewById(R.id.listaleki)

    }


    fun goListaLek() {
        val addBack = findViewById<Button>(R.id.listaleki)
        addBack.setOnClickListener {
            val intent = Intent(this, MedListActivity::class.java)
            startActivity(intent)
        }

    }





//    fun alarm(godzina: String, nazwa: String) {
//
//        val alarm1 = findViewById<ImageButton>(R.id.alarmbtn)
//        val alarm2 = findViewById<ImageButton>(R.id.alarmbtn2)
//        val alarm3 = findViewById<ImageButton>(R.id.alarmbtn3)
//
//        val hourNum = godzina?.filter { it == ',' }?.count()
//
//        if (hourNum == 0) {
//
//            alarm1.setOnClickListener {
//
//                val h = godzina?.substring(0, 2)?.toInt()
//                val m = godzina?.substring(3, 5)?.toInt()
//                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
//                intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//                intent.putExtra(AlarmClock.EXTRA_HOUR, h)
//                intent.putExtra(AlarmClock.EXTRA_MINUTES, m)
//                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Weź lek $nazwa")
//                startActivity(intent)
//                Toast.makeText(this, "Ustawiono alarm na godzinę $h:$m dla $nazwa",
//                    Toast.LENGTH_SHORT).show()
//            }
//
//
//        } else if (hourNum == 1) {
//
//
//
//            alarm1.setOnClickListener {
//
//                val h = godzina?.substring(0, 2)?.toInt()
//                val m = godzina?.substring(3, 5)?.toInt()
//                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
//                intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//                intent.putExtra(AlarmClock.EXTRA_HOUR, h)
//                intent.putExtra(AlarmClock.EXTRA_MINUTES, m)
//                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Weź lek $nazwa")
//                startActivity(intent)
//                Toast.makeText(this, "Ustawiono alarm na godzinę $h:$m dla $nazwa",
//                    Toast.LENGTH_SHORT).show()
//            }
//
//
//            alarm2.setOnClickListener {
//                val h2 = godzina?.substring(7, 9)?.toInt()
//                val m2 = godzina?.substring(10, 12)?.toInt()
//                val intent2 = Intent(AlarmClock.ACTION_SET_ALARM)
//                intent2.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//                intent2.putExtra(AlarmClock.EXTRA_HOUR, h2)
//                intent2.putExtra(AlarmClock.EXTRA_MINUTES, m2)
//                intent2.putExtra(AlarmClock.EXTRA_MESSAGE, "Weź lek $nazwa (dawka 2)")
//                startActivity(intent2)
//                Toast.makeText(
//                    this,
//                    "Ustawiono alarm na godzinę  $h2:$m2 dla $nazwa",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//
//        } else if (hourNum == 2) {
//
//
//            alarm1.setOnClickListener {
//
//                val h = godzina?.substring(0, 2)?.toInt()
//                val m = godzina?.substring(3, 5)?.toInt()
//                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
//                intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//                intent.putExtra(AlarmClock.EXTRA_HOUR, h)
//                intent.putExtra(AlarmClock.EXTRA_MINUTES, m)
//                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Weź lek $nazwa")
//                startActivity(intent)
//                Toast.makeText(
//                    this, "Ustawiono alarm na godzinę $h:$m dla $nazwa",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//
//            alarm2.setOnClickListener {
//                val h2 = godzina?.substring(7, 9)?.toInt()
//                val m2 = godzina?.substring(10, 12)?.toInt()
//                val intent2 = Intent(AlarmClock.ACTION_SET_ALARM)
//                intent2.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//                intent2.putExtra(AlarmClock.EXTRA_HOUR, h2)
//                intent2.putExtra(AlarmClock.EXTRA_MINUTES, m2)
//                intent2.putExtra(AlarmClock.EXTRA_MESSAGE, "Weź lek $nazwa (dawka 2)")
//                startActivity(intent2)
//                Toast.makeText(
//                    this,
//                    "Ustawiono alarm na godzinę  $h2:$m2 dla $nazwa",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//
//            alarm3.setOnClickListener {
//                val h3 = godzina?.substring(14, 16)?.toInt()
//                val m3 = godzina?.substring(17, 19)?.toInt()
//                val intent2 = Intent(AlarmClock.ACTION_SET_ALARM)
//                intent2.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//                intent2.putExtra(AlarmClock.EXTRA_HOUR, h3)
//                intent2.putExtra(AlarmClock.EXTRA_MINUTES, m3)
//                intent2.putExtra(AlarmClock.EXTRA_MESSAGE, "Weź lek $nazwa (dawka 2)")
//                startActivity(intent2)
//                Toast.makeText(
//                    this,
//                    "Ustawiono alarm na godzinę  $h3:$m3 dla $nazwa",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//        }
//
//    }
}






