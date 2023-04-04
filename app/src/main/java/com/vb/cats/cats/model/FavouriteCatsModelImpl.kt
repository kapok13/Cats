package com.vb.cats.cats.model

import com.vb.cats.database.database.CatsDatabase
import com.vb.cats.database.entity.Cat
import io.reactivex.rxjava3.core.Observable

class FavouriteCatsModelImpl(
    private val catsDatabase: CatsDatabase
) : FavouriteCatsModel {
    override fun getFavouriteCats(): Observable<List<Cat>> = catsDatabase.catsDao().getCats()

    override fun insertCat(cat: Cat) {
        catsDatabase.catsDao().insert(cat)
    }
}
