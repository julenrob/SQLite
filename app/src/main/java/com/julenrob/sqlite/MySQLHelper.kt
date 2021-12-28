package com.julenrob.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLHelper(context: Context): SQLiteOpenHelper(
    context, "amigos.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE amigos " + "(id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ",nombre TEXT" + ",email TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS amigos"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun addDato(nombre: String, email: String) {
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("email", email)
        val db = this.writableDatabase /*Abrimos la base de datos en modo escritura*/
        db.insert("amigos", null, datos)
        db.close()
    }
    fun mostrarDatos(): Cursor? {
        val db : SQLiteDatabase = this.readableDatabase /*Abrimos la base de datos en modo lectura*/
        val cursor = db.rawQuery(
            "SELECT * FROM amigos",
            null)
        return cursor
    }

    fun borrarDato(id: Int): Int{
        var args = arrayOf(id.toString())
        val db : SQLiteDatabase = this.writableDatabase
        val cursor = db.delete("amigos", "id=?", args)

        db.close()

        return cursor
    }

    fun actualizarDato(id: Int, newName: String, newEmail: String): Int{
        println("--- id: " + id)
        println("--- newName: " + newName)
        println("--- newEmail: " + newEmail)
        if (id.equals(null) || newName == "" || newEmail == ""){
            return 0
        }
        var values = ContentValues()
        values.put("nombre", newName)
        values.put("email", newEmail)
        var db : SQLiteDatabase = this.writableDatabase
        val success = db.update("amigos", values,"id=?", arrayOf(id.toString()))

        return success
    }


}