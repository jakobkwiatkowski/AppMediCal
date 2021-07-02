package edu.ib.medical

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DocDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "doc.db"
        private const val TABLE_NAME = "lekarze"
        private const val ID = "id"
        private const val name = "doc_name"
        private const val spec = "doc_spec"
        private const val phone = "doc_phone"
        private const val email = "doc_email"
        private const val adress = "doc_adress"
        private const val city = "doc_city"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableDoc = ("CREATE TABLE " + DocDatabaseHelper.TABLE_NAME +
                "(" + DocDatabaseHelper.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DocDatabaseHelper.name + " TEXT," +
                DocDatabaseHelper.spec + " TEXT," +
                DocDatabaseHelper.phone + " TEXT," +
                DocDatabaseHelper.email + " TEXT," +
                DocDatabaseHelper.adress + " TEXT," +
                DocDatabaseHelper.city + " TEXT" + ")")
        db?.execSQL(createTableDoc)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addDoc(doc: DocModel): Long {
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

        cv.put(name, doc.name)
        cv.put(spec, doc.spec)
        cv.put(phone, doc.phone)
        cv.put(email, doc.email)
        cv.put(adress, doc.adress)
        cv.put(city, doc.city)

        val success = db.insert(TABLE_NAME, null, cv)
        db.close()
        return success
    }

    fun getAllDocs(): ArrayList<DocModel> {
        val docList: ArrayList<DocModel> = ArrayList()
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
        var name: String
        var spec: String
        var phone: String
        var email: String
        var adress: String
        var city: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("doc_name"))
                spec = cursor.getString(cursor.getColumnIndex("doc_spec"))
                phone = cursor.getString(cursor.getColumnIndex("doc_phone"))
                email = cursor.getString(cursor.getColumnIndex("doc_email"))
                adress = cursor.getString(cursor.getColumnIndex("doc_adress"))
                city = cursor.getString(cursor.getColumnIndex("doc_city"))

                val doc = DocModel(
                    id = id,
                    name = name,
                    spec = spec,
                    phone = phone,
                    email = email,
                    adress = adress,
                    city = city
                )
                docList.add(doc)
            } while (cursor.moveToNext())
        }
        return docList
    }

    fun updateDoc(docid:Int, docname:String, docspec:String, docphone:String, docemail:String, docadress:String, doccity:String ): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        var result: Boolean = false

        cv.put(name, docname)
        cv.put(spec, docspec)
        cv.put(phone, docphone)
        cv.put(email, docemail)
        cv.put(adress, docadress)
        cv.put(city, doccity)

        try {

            db.update(TABLE_NAME, cv, "id=" + docid, null)
            result = true
        } catch(e: java.lang.Exception){
            result = false
        }
        return result
    }

    fun deleteDocById(id: Int): Int {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(ID, id)
        val success = db.delete(TABLE_NAME, "id = $id", null)
        db.close()
        return success
    }
}