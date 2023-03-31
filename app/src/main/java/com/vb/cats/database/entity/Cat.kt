package com.vb.cats.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Cat(
    @PrimaryKey val id: String,
    val url: String,
    var isFavourite : Boolean?
)
