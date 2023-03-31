package com.vb.cats.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vb.cats.database.entity.Cat
import io.reactivex.rxjava3.core.Observable

@Dao
interface CatsDao {
    @Query("SELECT * FROM cats")
    fun getCats(): Observable<List<Cat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cat: Cat)
}
