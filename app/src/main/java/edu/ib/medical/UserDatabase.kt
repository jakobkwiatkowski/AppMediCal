package edu.ib.medical

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UserDatabase(context: Context): SQLiteOpenHelper(context, NAME, null, VERSION) { //tworzenie tabeli

    companion object {
        private const val VERSION = 1
        private const val NAME = "User.db"
        private const val TabName = "Użytkownicy"
        private const val UserName = "user"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUser = ("CREATE TABLE " + TabName +
                "(" + UserName + " TEXT" + ")")
        db?.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TabName")
        onCreate(db)
    }

    fun addUser(username: String): Long {
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

        cv.put(UserName, username)

        val succes = db.insert(TabName, null, cv)
        db.close()
        return succes
    }

    fun getUser(): String {

        var user = ""
        val selectQuery = "SELECT * FROM $TabName"
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return user
        }


        if (cursor.moveToFirst()) {

            do {
                user = cursor.getString(cursor.getColumnIndex("user"))

            } while (cursor.moveToNext())
        }
        return user
    }

}








