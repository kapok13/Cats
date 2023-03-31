package com.vb.cats.modules

import androidx.room.Room
import com.vb.cats.database.database.CatsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "cats_db"

val databaseModule = module {
    single<CatsDatabase> {
        Room.databaseBuilder(
            androidContext(),
            CatsDatabase::class.java, DATABASE_NAME
        ).build()
    }
}
