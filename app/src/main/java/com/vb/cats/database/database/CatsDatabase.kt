package com.vb.cats.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vb.cats.database.dao.CatsDao
import com.vb.cats.database.entity.Cat

@Database(entities = [Cat::class], version = 1)
abstract class CatsDatabase : RoomDatabase() {
    abstract fun catsDao(): CatsDao
}
