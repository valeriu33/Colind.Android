package com.valeriu.colindandroid.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.valeriu.colindandroid.data.models.ColindEntity

@Database(entities = [ColindEntity::class], version = 2, exportSchema = false)
abstract class ColindDatabase : RoomDatabase() {

    abstract fun colindDao(): ColindDao
}