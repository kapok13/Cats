package com.vb.cats.cats.model

import com.vb.cats.database.entity.Cat
import io.reactivex.rxjava3.core.Observable

interface FavouriteCatsModel {
    fun getFavouriteCats() : Observable<List<Cat>>
    fun insertCat(cat: Cat)
}
