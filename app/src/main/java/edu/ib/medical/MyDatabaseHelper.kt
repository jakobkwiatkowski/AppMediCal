package edu.ib.medical

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASE_NAME: String ="MedLibrary.db"
val DATABASE_VERSION: Int =1

class MyDataBaseHelper(val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) { //tworzenie tabeli


    val TABLE_NAME = "Leki"
    val COLUMN_ID ="_id"
    val COLUMN_NazwaLeku = "nazwa_leku"
    val COLUMN_Dawka= "dawka_leku"
    val COLUMN_DataRozpoczecia = "data"
    val COLUMN_Czestotliwosc = "czestotliwość"
    val COLUMN_IleRazy ="ile_razy"
    val COLUMN_Godzina = "godzina"
    val COLUMN_Przypomnienie = "przypomnienie"
    val COLUMN_ZapasTabletek ="zapas_tabletek"
    val COLUMN_KoniecLeku = "przypomnienie_o_braku_leków"

    override fun onCreate(db: SQLiteDatabase?) {
      val SQL_CREATE_TABLE: String =
                "CREATE TABLE" + TABLE_NAME +
                        "("  +  COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NazwaLeku + " TEXT, " +
                        COLUMN_Dawka + "T EXT, " +
                        COLUMN_DataRozpoczecia + " TEXT, " +
                        COLUMN_Czestotliwosc+ " TEXT, " +
                        COLUMN_IleRazy + " TEXT, " +
                        COLUMN_Godzina + " TEXT, " +
                        COLUMN_Przypomnienie + " TEXT, " +
                        COLUMN_ZapasTabletek + " TEXT, " +
                        COLUMN_KoniecLeku + " TEXT);"


       db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    fun addLek(nazwa_leku: String, dawka_leku:String, data:String, czestotliwosc:String, ile_razy:String, godzina: String, przypomnienie:String, zapas:String, koniec_leku:String){
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(COLUMN_NazwaLeku, nazwa_leku)
        cv.put(COLUMN_Dawka, dawka_leku)
        cv.put(COLUMN_DataRozpoczecia, data)
        cv.put(COLUMN_Czestotliwosc, czestotliwosc)
        cv.put(COLUMN_IleRazy, ile_razy)
        cv.put(COLUMN_Godzina, godzina)
        cv.put(COLUMN_Przypomnienie, przypomnienie)
        cv.put(COLUMN_ZapasTabletek, zapas)
        cv.put(COLUMN_KoniecLeku, koniec_leku)

        val result: Long = db.insert(TABLE_NAME, null, cv)
        if(result == null) {
            Toast.makeText(context, "Nie udało się dodać leku", Toast.LENGTH_SHORT).show()
        } else{

            Toast.makeText(context, "Dodano lek do listy leków", Toast.LENGTH_SHORT).show()
        }
    }

    fun readAllData(): Cursor{
        val query = "SELECTED * FROM" + TABLE_NAME
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery(query, null);
        return cursor

    }

}







