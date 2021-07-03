package edu.ib.medical


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class LekAdapter(ctx: Context): RecyclerView.Adapter<LekAdapter.LekViewHolder>(){

    val ctx = ctx
    private var lekList: ArrayList<LekModel> = ArrayList()
    private var onClickDeleteItem:((LekModel) ->Unit)? = null


    fun addItems(items: ArrayList<LekModel>) {
        this.lekList = items
        notifyDataSetChanged()
    }




    fun setOnClickDeleteItem(callback:(LekModel)->Unit){
        this.onClickDeleteItem = callback
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LekViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
    )


    override fun onBindViewHolder(holder: LekViewHolder, position: Int) {
        val lek = lekList[position]
        holder.id.text = lek.id.toString()
        holder.nazwa.text = lek.nazwa
        holder.dawka.text = lek.dawka
        holder.data.text = lek.data
        holder.czestotliwosc.text = lek.czestotliwosc
        holder.ileRazy.text = lek.ileRazy
        holder.godzina.text = lek.godzina
        holder.zapas.text = lek.zapas
        holder.koniec.text = lek.koniec


        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(lek) }

        holder.btnUpdate.setOnClickListener {
            val inflater: LayoutInflater = LayoutInflater.from(ctx)
            val view: View = inflater.inflate(R.layout.updatelek, null)

            val editNazwa: TextView = view.findViewById(R.id.nazwalekuedit)
            val editDawka: TextView = view.findViewById(R.id.dawkaedit)
            val editData: TextView = view.findViewById(R.id.wybierzdateedit)
            val editCzesto: TextView = view.findViewById(R.id.czestotliwoscedit)
            val editIlerazy: TextView = view.findViewById(R.id.ilerazyedit)
            val editGodzina: TextView = view.findViewById(R.id.wybierzgodzineedit)
            val editZapas: TextView = view.findViewById(R.id.zapasedit)
            val editKoniec: TextView = view.findViewById(R.id.konieclekuedit)


            val spinner22: Spinner = view.findViewById(R.id.czestoedit)
            val czesto = arrayOf("codziennie", "co 2 dni", "co 3 dni", "co 4 dni", "co 5 dni", "co 6 dni", "co tydzień", "co 2 tygodnie", "co miesiąc")
            val  arrayAdapter22 = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, czesto)

            spinner22.adapter = arrayAdapter22
            spinner22.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    editCzesto.text = czesto[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }


            val spinner33: Spinner = view.findViewById(R.id.ileedit)
            val ilerazy = arrayOf("raz dziennie", "2 razy dziennie", "3 razy dziennie")
            val  arrayAdapter33 = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, ilerazy)

            spinner33.adapter = arrayAdapter33
            spinner33.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    editIlerazy.text = ilerazy[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }


            val spinner44: Spinner = view.findViewById(R.id.przypomnijedit)
            val koniec = arrayOf("2 dni przed końcem", "3 dni przed końcem", "4 dni przed końcem", "5 dni przed końcem", "6 dni przed końcem", "7 dni przed końcem")
            val  arrayAdapter44 = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, koniec)

            spinner44.adapter = arrayAdapter44
            spinner44.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    editKoniec.text = koniec[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }



            val kalendarz = Calendar.getInstance()

            val kiedyedit: Button = view.findViewById(R.id.terminbtnedit)

            kiedyedit.setOnClickListener {
                val dpd = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    kalendarz.set(Calendar.YEAR, year)
                    kalendarz.set(Calendar.MONTH, month)
                    kalendarz.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    editData.text = SimpleDateFormat("dd-MM-yyy").format(kalendarz.time)

                }
                DatePickerDialog(
                    ctx,
                    dpd,
                    kalendarz.get(Calendar.YEAR),
                    kalendarz.get(Calendar.MONTH),
                    kalendarz.get(Calendar.DAY_OF_MONTH)
                ).show()
            }


            val godzedit: Button = view.findViewById(R.id.czasedit)
            val lista2 = mutableListOf<String>()

            var godzina2 = ""


            godzedit.setOnClickListener {
                val dpd2 = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->

                    kalendarz.set(Calendar.HOUR_OF_DAY, hour)
                    kalendarz.set(Calendar.MINUTE, minute)

                    godzina2 = SimpleDateFormat("HH:mm").format(kalendarz.time)
                    lista2.add(godzina2)
                    editGodzina.text = (lista2.toString().replace("[", "").replace("]",""))
                }

                TimePickerDialog(
                    ctx,
                    dpd2,
                    kalendarz.get(Calendar.HOUR_OF_DAY),
                    kalendarz.get(Calendar.MINUTE),
                    true
                ).show()
            }



            editNazwa.text = lek.nazwa
            editDawka.text = lek.dawka
            editData.text = lek.data
            editCzesto.text = lek.czestotliwosc
            editIlerazy.text = lek.ileRazy
            editGodzina.text = lek.godzina
            editZapas.text = lek.zapas
            editKoniec.text = lek.koniec

            val builder = AlertDialog.Builder(ctx)
                .setTitle("Aktualizuj dane leku")
                .setView(view)
                .setPositiveButton("Aktualizuj", DialogInterface.OnClickListener { dialog, which ->
                    val isUpdate: Boolean = MedListActivity.sqliteHelper.updateLek(
                        lek.id,
                        editNazwa.text.toString(),
                        editDawka.text.toString(),
                        editData.text.toString(),
                        editCzesto.text.toString(),
                        editIlerazy.text.toString(),
                        editGodzina.text.toString(),
                        editZapas.text.toString(),
                        editKoniec.text.toString()
                    )

                    if (isUpdate == true) {
                        lekList[position].nazwa = editNazwa.text.toString()
                        lekList[position].dawka = editDawka.text.toString()
                        lekList[position].data = editData.text.toString()
                        lekList[position].czestotliwosc = editCzesto.text.toString()
                        lekList[position].ileRazy = editIlerazy.text.toString()
                        lekList[position].godzina = editGodzina.text.toString()
                        lekList[position].zapas = editZapas.text.toString()
                        lekList[position].koniec = editKoniec.text.toString()
                        notifyDataSetChanged()
                        Toast.makeText(ctx, "Aktualizacja udana", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(ctx, "Błąd aktualizacji", Toast.LENGTH_SHORT).show()
                    }

                }).setNegativeButton("Zamknij", DialogInterface.OnClickListener { dialog, which ->

                })
            val alert = builder.create()
            alert.show()

        }
    }

    override fun getItemCount(): Int {
        return lekList.size
    }


    class LekViewHolder(var view:View): RecyclerView.ViewHolder(view){

        var id = view.findViewById<TextView>(R.id.id_leku_txt)
        var nazwa = view.findViewById<TextView>(R.id.nazwa_leku_txt)
        var dawka = view.findViewById<TextView>(R.id.dawka_leku_txt)
        var data = view.findViewById<TextView>(R.id.data_rozpoczecia_txt)
        var czestotliwosc =  view.findViewById<TextView>(R.id.czestotliwosc_txt)
        var ileRazy =  view.findViewById<TextView>(R.id.ile_razy_txt)
        var godzina =  view.findViewById<TextView>(R.id.godzina_txt)
        var zapas =  view.findViewById<TextView>(R.id.zapas_tabletek_txt)
        var koniec = view.findViewById<TextView>(R.id.koniec_leku_txt)

        var btnDelete = view.findViewById<ImageButton>(R.id.usun)
        var btnUpdate = view.findViewById<ImageButton>(R.id.zmien)
    }
}





