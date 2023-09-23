package io.fishingcoach.model.database

import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import io.fishingcoach.App
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream


class FishDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(
    ctx,
    DB_NAME, null,
    DB_VERSION
) {

    companion object {
        val DB_NAME = "fishingcoach"
        val ASSETS_PATH = "databases"
        val DB_VERSION = 1
        val instance by lazy { FishDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    private fun installDatabaseFromAssets() {
        val inputStream = App.instance.assets.open("$ASSETS_PATH/$DB_NAME.db")
        try {
            val outputFile = File(App.instance.getDatabasePath(DB_NAME).path)
            val outputStream = FileOutputStream(outputFile)

            inputStream.copyTo(outputStream)
            inputStream.close()

            outputStream.flush()
            outputStream.close()
        } catch (exception: Throwable) {
            throw RuntimeException("The $DB_NAME database couldn't be installed.", exception)
        }
    }

    private val preferences: SharedPreferences = App.instance.getSharedPreferences(
        "${App.instance.packageName}.database_versions",
        Context.MODE_PRIVATE
    )

    private fun installedDatabaseIsOutdated(): Boolean {
        return preferences.getInt(DB_NAME, 0) < DB_VERSION
    }

    private fun writeDatabaseVersionInPreferences() {
        preferences.edit().apply {
            putInt(DB_NAME, DB_VERSION)
            apply()
        }
    }

    @Synchronized
    private fun installOrUpdateIfNecessary() {
        if (installedDatabaseIsOutdated()) {
            App.instance.deleteDatabase(DB_NAME)
            installDatabaseFromAssets()
            writeDatabaseVersionInPreferences()
        }
    }

    override fun getReadableDatabase(): SQLiteDatabase {
        installOrUpdateIfNecessary()
        return super.getReadableDatabase()
    }

    /*override fun getWritableDatabase(): SQLiteDatabase {
        throw RuntimeException("The $DB_NAME database is not writable.")
    }*/

}