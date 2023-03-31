package com.vb.cats.cats.model

import io.reactivex.rxjava3.core.Observable

interface CatsModel {
    val cats: Observable<List<CatResponse>>
    fun getNextPage()
    fun resetPage()
}
