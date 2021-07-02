package edu.ib.medical

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MyDataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) { //tworzenie tabeli

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Leki.db"
        private const val TABLE_NAME = "Leki"
        private const val ID = "id"
        private const val NazwaLeku = "nazwa_leku"
        private const val Dawka = "dawka_leku"
        private const val DataRozpoczecia = "data"
        private const val Czestotliwosc = "czestotliwosc"
        private const val IleRazy = "ile_razy"
        private const val Godzina = "godzina"

        //        private const val Przypomnienie = "przypomnienie"
        private const val ZapasTabletek = "zapas_tabletek"
        private const val KoniecLeku = "koniec"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableLeki = ("CREATE TABLE " + TABLE_NAME +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NazwaLeku + " TEXT," +
                Dawka + " TEXT," +
                DataRozpoczecia + " TEXT," +
                Czestotliwosc + " TEXT," +
                IleRazy + " TEXT," +
                Godzina + " TEXT," +
                ZapasTabletek + " TEXT," +
                KoniecLeku + " TEXT" + ")")
        db?.execSQL(createTableLeki)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addLek(lek: LekModel): Long {
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

//        cv.put(ID, lek.id)
        cv.put(NazwaLeku, lek.nazwa)
        cv.put(Dawka, lek.dawka)
        cv.put(DataRozpoczecia, lek.data)
        cv.put(Czestotliwosc, lek.czestotliwosc)
        cv.put(IleRazy, lek.ileRazy)
        cv.put(Godzina, lek.godzina)
//        cv.put(Przypomnienie, lek.przypomnienie)
        cv.put(ZapasTabletek, lek.zapas)
        cv.put(KoniecLeku, lek.koniec)

        val succes = db.insert(TABLE_NAME, null, cv)
        db.close()
        return succes
    }

    fun getAllLeki(): ArrayList<LekModel> {

        val lekList: ArrayList<LekModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var nazwa: String
        var dawka: String
        var data: String
        var czestotliwosc: String
        var ileRazy: String
        var godzina: String
//        var przypomnienie: String
        var zapas: String
        var koniec: String


        if (cursor.moveToFirst()) {

            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                nazwa = cursor.getString(cursor.getColumnIndex("nazwa_leku"))
                dawka = cursor.getString(cursor.getColumnIndex("dawka_leku"))
                data = cursor.getString(cursor.getColumnIndex("data"))
                czestotliwosc = cursor.getString(cursor.getColumnIndex("czestotliwosc"))
                ileRazy = cursor.getString(cursor.getColumnIndex("ile_razy"))
                godzina = cursor.getString(cursor.getColumnIndex("godzina"))
                zapas = cursor.getString(cursor.getColumnIndex("zapas_tabletek"))
                koniec = cursor.getString(cursor.getColumnIndex("koniec"))

                val lek = LekModel(
                    id = id,
                    nazwa = nazwa,
                    dawka = dawka,
                    data = data,
                    czestotliwosc = czestotliwosc,
                    ileRazy = ileRazy,
                    godzina = godzina,
                    zapas = zapas,
                    koniec = koniec
                )
                lekList.add(lek)
            } while (cursor.moveToNext())
        }
        return lekList
    }


    fun updateLek(lekid:Int, leknazwa:String, lekdawka:String, lekdata:String, lekczesto:String,
    lekile:String, lekgodzina:String, lekzapas:String, lekkoniec:String): Boolean {

        val db = this.writableDatabase
        val cv = ContentValues()
        var result:Boolean = false

        cv.put(NazwaLeku, leknazwa)
        cv.put(Dawka, lekdawka)
        cv.put(DataRozpoczecia, lekdata)
        cv.put(Czestotliwosc, lekczesto)
        cv.put(IleRazy, lekile)
        cv.put(Godzina, lekgodzina)
        cv.put(ZapasTabletek, lekzapas)
        cv.put(KoniecLeku, lekkoniec)

        try {
            db.update(TABLE_NAME, cv, "id=" + lekid, null)
            result = true
        }catch(e: java.lang.Exception){
            result = false
        }
        return result


    }




    fun deleteLekById(id: Int): Int {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(ID, id)

        val success = db.delete(TABLE_NAME, "id=$id", null)
        db.close()
        return success

    }

}














