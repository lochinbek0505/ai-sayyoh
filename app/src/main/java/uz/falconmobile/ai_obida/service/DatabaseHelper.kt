package uz.falconmobile.ai_obida.service

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import uz.falconmobile.ai_obida.models.locate_model

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "locateModel.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_LOCATE = "locate_model"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME_UZ = "name_uz"
        private const val COLUMN_NAME_ENG = "name_eng"
        private const val COLUMN_NAME_TURK = "name_turk"
        private const val COLUMN_NAME_RU = "name_ru"
        private const val COLUMN_TEXT_UZ = "text_uz"
        private const val COLUMN_TEXT_ENG = "text_eng"
        private const val COLUMN_TEXT_TURK = "text_turk"
        private const val COLUMN_TEXT_RU = "text_ru"
        private const val COLUMN_IMAGE = "image"
        private const val COLUMN_VOICE_UZ = "voice_uz"
        private const val COLUMN_VOICE_ENG = "voice_eng"
        private const val COLUMN_VOICE_TURK = "voice_turk"
        private const val COLUMN_VOICE_RU = "voice_ru"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_LOCATE (" +
                "$COLUMN_ID TEXT PRIMARY KEY," +
                "$COLUMN_NAME_UZ TEXT," +
                "$COLUMN_NAME_ENG TEXT," +
                "$COLUMN_NAME_TURK TEXT," +
                "$COLUMN_NAME_RU TEXT," +
                "$COLUMN_TEXT_UZ TEXT," +
                "$COLUMN_TEXT_ENG TEXT," +
                "$COLUMN_TEXT_TURK TEXT," +
                "$COLUMN_TEXT_RU TEXT," +
                "$COLUMN_IMAGE INTEGER," +
                "$COLUMN_VOICE_UZ TEXT," +
                "$COLUMN_VOICE_ENG TEXT," +
                "$COLUMN_VOICE_TURK TEXT," +
                "$COLUMN_VOICE_RU TEXT" +
                ")"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_LOCATE")
            onCreate(db)
        }
    }

    // Insert a record
    fun insertLocateModel(model: locate_model): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, model.id)
            put(COLUMN_NAME_UZ, model.name_uz)
            put(COLUMN_NAME_ENG, model.name_eng)
            put(COLUMN_NAME_TURK, model.name_turk)
            put(COLUMN_NAME_RU, model.name_ru)
            put(COLUMN_TEXT_UZ, model.text_uz)
            put(COLUMN_TEXT_ENG, model.text_eng)
            put(COLUMN_TEXT_TURK, model.text_turk)
            put(COLUMN_TEXT_RU, model.text_ru)
            put(COLUMN_IMAGE, model.image)
            put(COLUMN_VOICE_UZ, model.voice_uz)
            put(COLUMN_VOICE_ENG, model.voice_eng)
            put(COLUMN_VOICE_TURK, model.voice_turk)
            put(COLUMN_VOICE_RU, model.voice_ru)
        }

        val result = db.insert(TABLE_LOCATE, null, values)
        db.close()
        return result
    }

    // Delete a record by ID
    fun deleteLocateModel(id: String): Int {
        val db = writableDatabase
        val result = db.delete(TABLE_LOCATE, "$COLUMN_ID = ?", arrayOf(id))
        db.close()
        return result
    }

    // Check if a record exists
    fun isLocateModelExists(id: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_LOCATE,
            arrayOf(COLUMN_ID),
            "$COLUMN_ID = ?",
            arrayOf(id),
            null,
            null,
            null
        )
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    // Get all records (optional)
    @SuppressLint("Range")
    fun getAllLocateModels(): List<locate_model> {
        val models = mutableListOf<locate_model>()
        val db = readableDatabase
        val cursor = db.query(TABLE_LOCATE, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val model = locate_model(
                    name_uz = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_UZ)),
                    name_eng = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ENG)),
                    name_turk = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TURK)),
                    name_ru = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_RU)),
                    text_uz = cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_UZ)),
                    text_eng = cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_ENG)),
                    text_turk = cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_TURK)),
                    text_ru = cursor.getString(cursor.getColumnIndex(COLUMN_TEXT_RU)),
                    image = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)),
                    voice_uz = cursor.getString(cursor.getColumnIndex(COLUMN_VOICE_UZ)),
                    voice_eng = cursor.getString(cursor.getColumnIndex(COLUMN_VOICE_ENG)),
                    voice_turk = cursor.getString(cursor.getColumnIndex(COLUMN_VOICE_TURK)),
                    voice_ru = cursor.getString(cursor.getColumnIndex(COLUMN_VOICE_RU)),
                    id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
                )
                models.add(model)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return models
    }
}
